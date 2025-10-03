package net.mehvahdjukaar.stone_zone;

import net.mehvahdjukaar.stone_zone.modules.better_archeology.BetterArcheologyModule;
import net.mehvahdjukaar.stone_zone.modules.blockus.BlockusModule;
import net.mehvahdjukaar.stone_zone.modules.building_but_better.BuildingButBetterModule;
import net.mehvahdjukaar.stone_zone.modules.chipped.ChippedModule;
import net.mehvahdjukaar.stone_zone.modules.handcrafted.HandcraftedModule;
import net.mehvahdjukaar.stone_zone.modules.lets_do.CandlelightModule;
import net.mehvahdjukaar.stone_zone.modules.more_beautiful_torches.MoreBeautifulTorches;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkModule;
import net.mehvahdjukaar.stone_zone.modules.quark.QuarkMudModule;
import net.mehvahdjukaar.stone_zone.modules.stoneworks.StoneworksModule;
import net.mehvahdjukaar.stone_zone.modules.twigs.TwigsModule;
import net.mehvahdjukaar.stone_zone.modules.waystones.WaystonesModule;
import net.mehvahdjukaar.stone_zone.modules.wraith_waystones.WraithWaystonesModule;

import static net.mehvahdjukaar.every_compat.EveryCompat.addOtherCompatMod;
import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addIfLoaded;
import static net.mehvahdjukaar.every_compat.api.EveryCompatAPI.addMultipleIfLoaded;

public class StoneZoneCommon {

    protected void initialize() {
        StoneZone.init();

        this.addModules();
    }

    protected void addModules() {


//!! =============================================== Add Other Compat Mods ========================================== \\
        addOtherCompatMod("rechiseledcreate", "create", "rechiseled");
        addOtherCompatMod("rechiseledae2", "ae2", "rechiseled");

//!! =================================================== Add Modules ================================================ \\

        addIfLoaded("bbb", () -> BuildingButBetterModule.class);
        addIfLoaded("betterarcheology", () -> BetterArcheologyModule.class);
        addIfLoaded("blockus", () -> BlockusModule.class);
        addIfLoaded("candlelight", () -> CandlelightModule.class); //!! [Let's Do]
        addIfLoaded("chipped", () -> ChippedModule.class);
//        addIfLoaded("decorative_blocks", () -> DecorativeBlocksModule.class); // NOT AVAILABLE
        addIfLoaded("fwaystones", () -> WraithWaystonesModule.class);
        addIfLoaded("handcrafted", () -> HandcraftedModule.class);
        addIfLoaded("more_beautiful_torches", () -> MoreBeautifulTorches.class);
        addIfLoaded("stoneworks", () -> StoneworksModule.class);
        addIfLoaded("twigs", () -> TwigsModule.class);
        addMultipleIfLoaded("quark", () -> QuarkModule.class, () -> QuarkMudModule.class);
        addIfLoaded("waystones", () -> WaystonesModule.class);

//!! ====================================================== OTHERS ================================================== \\

    }
}
