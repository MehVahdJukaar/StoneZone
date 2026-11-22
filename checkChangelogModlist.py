#!/usr/bin/env python3
import argparse
import asyncio
import json
import os
import re
from concurrent.futures import ThreadPoolExecutor
from urllib.error import HTTPError
from urllib.request import Request, urlopen

""" USAGE
• Check all mods in changelog-modlist.md (read-only):
python updateChangelogModlist.py

• Check for a specific Minecraft version:
python updateChangelogModlist.py --mc-version 26.1.2

• Apply availability emojis directly to changelog-modlist.md:
python updateChangelogModlist.py --apply
"""

# ANSI Text Formatting Constants
ANSI_RESET = "\033[0m"
BG_RED = "\033[48;2;180;35;35m"        # Deep Crimson Red
BG_GREEN = "\033[48;2;25;135;84m"      # Rich Forest Green
BG_CYAN = "\033[48;2;13;110;135m"      # Deep Ocean Cyan
BG_YELLOW = "\033[48;2;140;100;10m"    # Dark Ochre / Mustard
TEXT_BLACK = "\033[38;2;15;15;15m"     # Crisp Black
TEXT_WHITE = "\033[1;38;2;255;255;255m"  # Bold Pure White

MODS_PROJECTID_PATTERN = re.compile(r"([A-Za-z' \]\[\\&]+)\(([\d]+)\)")
TABLE_ROW_PATTERN = re.compile(r"^(\|\s*[^|]+\|\s*)(🟩|🟥|❔|🔧)(\s*\|.*)$")
TARGET_FILE = "changelog-modlist.md"


def fetch_mod_data(project_id: str):
    """Synchronous network worker executing inside the ThreadPoolExecutor."""
    url = f"https://api.cfwidget.com/{project_id}"
    req = Request(url, headers={"User-Agent": "Python-AsyncCurseUpdateChecker/3.0"})
    with urlopen(req, timeout=10) as response:
        return json.loads(response.read().decode("utf-8"))
    return None


async def check_single_mod(mod_name: str, project_id: str, mc_version: str, executor: ThreadPoolExecutor):
    """Checks Fabric and NeoForge availability for a given project ID and target MC version."""
    loop = asyncio.get_running_loop()
    has_fabric = False
    has_neoforge = False
    error_msg = None

    try:
        data = await loop.run_in_executor(executor, fetch_mod_data, project_id)
        files = data.get("files", [])

        for current_file in files:
            versions = [str(v).lower() for v in current_file.get("versions", [])]
            if mc_version.lower() in versions:
                if "fabric" in versions:
                    has_fabric = True
                if "neoforge" in versions:
                    has_neoforge = True

            if has_fabric and has_neoforge:
                break

    except HTTPError as e:
        error_msg = f"HTTP {e.code}"
    except Exception as e:
        error_msg = str(e)

    return {
        "name": mod_name,
        "project_id": project_id,
        "has_fabric": has_fabric,
        "has_neoforge": has_neoforge,
        "error": error_msg,
    }


def determine_status_emoji(section: str, res: dict) -> str:
    """Determines the Markdown table emoji based on the table column and loader availability."""
    has_fabric = res["has_fabric"]
    has_neoforge = res["has_neoforge"]

    if section == "FABRIC":
        return "🟩" if has_fabric else "❔"
    if section == "NEOFORGE":
        return "🟩" if has_neoforge else "❔"
    if section == "COMMON":
        if has_fabric and has_neoforge:
            return "🟩"
        if has_fabric or has_neoforge:
            return "🟥"  # Partially available, not yet Common
        return "❔"

    return "❔"


def format_status_line(result: dict, name_width: int = 40) -> str:
    """Formats the terminal output with aligned Fabric, NeoForge, or Common badges."""
    clean_name = result["name"].replace("\\", "").strip()
    padded_name = clean_name.ljust(name_width)

    if result["error"]:
        return (
            f"{padded_name} "
            f"{BG_YELLOW}{TEXT_WHITE}[ ERROR: {result['error']} ]{ANSI_RESET}\n"
        )

    if result["has_fabric"] and result["has_neoforge"]:
        return f"{padded_name} {BG_GREEN}{TEXT_WHITE}[ COMMON ]{ANSI_RESET}\n"
    if not result["has_fabric"] and not result["has_neoforge"]:
        return f"{padded_name} {BG_YELLOW}{TEXT_WHITE}[ UNKNOWN ]{ANSI_RESET}\n"

    fabric_bg = BG_GREEN if result["has_fabric"] else BG_RED
    neoforge_bg = BG_GREEN if result["has_neoforge"] else BG_RED

    fabric_badge = f"{fabric_bg}{TEXT_WHITE}[ FABRIC ]{ANSI_RESET}"
    neoforge_badge = f"{neoforge_bg}{TEXT_WHITE}[ NEOFORGE ]{ANSI_RESET}"

    return f"{padded_name} {fabric_badge} {neoforge_badge}\n"


async def process_changelog(file_path: str, mc_version: str, apply_updates: bool, max_workers: int):
    os.system("")

    if not os.path.exists(file_path):
        print(f"{BG_RED}{TEXT_WHITE}[ERROR]{ANSI_RESET} File not found: {file_path}")
        return

    with open(file_path, "r", encoding="utf-8") as f:
        lines = f.readlines()

    table_columns = ["FABRIC", "COMMON", "NEOFORGE"]
    td_index = -1
    in_table = False
    current_section = "GENERAL"

    entries = []
    for line_idx, line in enumerate(lines):
        stripped = line.strip()

        if "<table>" in stripped:
            in_table = True
            td_index = -1
        elif "</table>" in stripped:
            in_table = False
            current_section = "OTHER MODS"

        if in_table and "<td" in stripped:
            td_index += 1
            if td_index < len(table_columns):
                current_section = table_columns[td_index]

        # Ignore any line marked with 🟡 (built-in module)
        if "🟡" in line:
            continue

        for match in MODS_PROJECTID_PATTERN.finditer(line):
            entries.append({
                "line_idx": line_idx,
                "section": current_section,
                "full_match": match.group(0),
                "mod_name": match.group(1),
                "project_id": match.group(2),
            })

    if not entries:
        print(f"No mod entries matching pattern found in {file_path}.")
        return

    print(f"=== Checking {len(entries)} mods in {file_path} for Minecraft {mc_version} ===\n")

    with ThreadPoolExecutor(max_workers=max_workers) as executor:
        tasks = [
            check_single_mod(entry["mod_name"], entry["project_id"], mc_version, executor)
            for entry in entries
        ]
        results = await asyncio.gather(*tasks)

    # Print results grouped by table section with dividers
    last_section = None
    for entry, res in zip(entries, results):
        if entry["section"] != last_section:
            if last_section is not None:
                print("--------------------------------------------------")
            print(f"{BG_CYAN}{TEXT_WHITE} {entry['section']} {ANSI_RESET}\n")
            last_section = entry["section"]

        print(format_status_line(res))

    # Update the Markdown table emojis if --apply is passed
    if apply_updates:
        updated_lines = list(lines)
        modified_count = 0

        for entry, res in zip(entries, results):
            if res["error"] or entry["section"] not in table_columns:
                continue

            line_idx = entry["line_idx"]
            original_line = updated_lines[line_idx]

            # Double-check line does not contain 🟡 or 🔧 (planned later)
            if "🟡" in original_line or "🔧" in original_line:
                continue

            new_emoji = determine_status_emoji(entry["section"], res)
            new_line = TABLE_ROW_PATTERN.sub(rf"\g<1>{new_emoji}\g<3>", original_line, count=1)

            if new_line != original_line:
                updated_lines[line_idx] = new_line
                modified_count += 1

        with open(file_path, "w", encoding="utf-8") as f:
            f.writelines(updated_lines)

        print(f"\n{BG_CYAN}{TEXT_WHITE}[SAVED]{ANSI_RESET} Updated {modified_count} line(s) in {file_path}.")
    else:
        print(f"\n{BG_YELLOW}{TEXT_WHITE}[DRY-RUN]{ANSI_RESET} No changes written to {file_path}. Pass --apply to update the file.")


def main():
    parser = argparse.ArgumentParser(description="Check mod loader availability from changelog-modlist.md.")
    parser.add_argument("--apply", action="store_true", help="Write status emojis directly to changelog-modlist.md.")
    parser.add_argument("--mc-version", default="1.21.1", help="Target Minecraft version (default: 1.21.1).")
    parser.add_argument("--max-workers", type=int, default=16, help="Maximum concurrent API requests.")

    args = parser.parse_args()
    asyncio.run(process_changelog(TARGET_FILE, args.mc_version, args.apply, args.max_workers))


if __name__ == "__main__":
    main()
