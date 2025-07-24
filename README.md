# Stone Zone

Tired of incomplete stone sets? Well you came to the right place!

This Mod serves as an addon to many popular mods and is meant to fill in the gaps by adding all the blocks that such mods add in all stone types currently installed.

Every block type added is available in ALL stone types.

With this mod you won't need extra compatibility addons and instead only rely on a single universal one! It's all automatic!

Check out [CurseForge](https://www.curseforge.com/minecraft/mc-mods/stone-zone) or [Modrinth](https://modrinth.com/mod/stone-zone) for more details

## API to support your mod with Every Compat (Stone Zone)

### KEY POINTS to keep in mind:
1. If your mod is multi-platform with blocks' class being in COMMON, then you can put the module in COMMON to support your mod
2. If blocks' class is in FORGE and FABRIC, then you'll need to create 2 modules in both FABRIC & FORGE's folder

**NOTE:**

if your mod is already supported heavily consider reaching out to incorporate some EC integration code in your mod itself for future proofing/stability

### DEVELOPMENT:

Three Things are required:
- **Every Compat (Library Section)** OR **Every Compat (Wood Good)** - Wood Good has many modules that you can use for references
- **Every Compat (Stone Zone**
- **Moonlight Lib** used to be known as **Selene**

How to support your mod with Every Compat, Please look at the below:

`common/src/main/java/net/mehvahdjukaar/every_compat/api/EveryCompatAPI.java`

it has 2 usages:
1) Using `EveryCompatAPI.registerModule(new ExampleModule(mod_Id))` to add the module to EveryCompat to support your mod
2) Using `EveryCompatAPI.addOtherCompatMod(String, List<String>, List<String>)` to add a compat Mod that your mod is already supported with Stone/Mud Mods
3) Add an undetected StoneType or MudType from your mod, an example is provided in `EveryCompatAPI`

Take a look at how the module is supporting blocks or items from a mod (detail level is general):

`common/src/main/java/net/mehvahdjukaar/stone_zone/api/example/ExampleModule.java`
