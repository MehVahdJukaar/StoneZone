package net.mehvahdjukaar.stone_zone.modules.neoforge.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.MetalScaffoldingCTBehaviour;
import com.simibubi.create.content.decoration.encasing.EncasedCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogCTBehaviour;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.createmod.catnip.data.Couple;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import static com.simibubi.create.foundation.block.connected.CTSpriteShifter.getCT;

@SuppressWarnings("CommentedOutCode")
@OnlyIn(Dist.CLIENT)
public class CreateClientModule {

//    public static void registerWindowCTBehavior(StoneZoneModule module, SimpleEntrySet<StoneType, Block> window, SimpleEntrySet<StoneType, Block> window_pane) {
//        window.blocks.forEach((stoneType, block) -> {
//            String textureId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "palettes/ornate_", "window");
//
//            CTSpriteShiftEntry shiftEntry = vertical(textureId);
//
//            registerCTBehviour(block, new HorizontalCTBehaviour(shiftEntry));
//            registerCTBehviour(window_pane.blocks.get(stoneType), new GlassPaneCTBehaviour(shiftEntry));
//        });
//    }

    public static void registerCasingCTBehavior(StoneZoneModule module,
                                                SimpleEntrySet<StoneType, Block> casing_entry,
                                                SimpleEntrySet<StoneType, Block> encasedShaft_entry,
                                                SimpleEntrySet<StoneType, Block> encasedCogwheel_entry,
                                                SimpleEntrySet<StoneType, Block> encasedLargeCogwheel_entry

    ) {
        casing_entry.blocks.forEach((stoneType, block) -> {
            String casingId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "", "casing");
            String cogwheel_sideId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "", "encased_cogwheel_side");

            CTSpriteShiftEntry casingShift = omni(casingId);
            CTSpriteShiftEntry encasedCogwheelVertical = vertical(cogwheel_sideId);
            CTSpriteShiftEntry encasedCogwheelHorizontal = horizontal(cogwheel_sideId);

            /// CASING
            registerCTBehviour(block, new EncasedCTBehaviour(casingShift));

            CreateClient.CASING_CONNECTIVITY.makeCasing(block, casingShift);

            /// ENCASED_SHAFT
            Block encasedBlock = encasedShaft_entry.blocks.get(stoneType);

            registerCTBehviour(encasedBlock, new EncasedCTBehaviour(casingShift));
            CreateClient.CASING_CONNECTIVITY.make(encasedBlock, casingShift,
                    (blockState, face) -> face.getAxis() != blockState.getValue(EncasedShaftBlock.AXIS));

            /// ENCASED_COGWHEEL
            Block encasedCogwheelBlock = encasedCogwheel_entry.blocks.get(stoneType);

            registerCTBehviour(encasedCogwheelBlock, new EncasedCogCTBehaviour(casingShift,
                    Couple.create(encasedCogwheelVertical, encasedCogwheelHorizontal)));

            CreateClient.CASING_CONNECTIVITY.make(encasedCogwheelBlock, casingShift,
                    (blockState, face) ->
                            face.getAxis() == blockState.getValue(EncasedCogwheelBlock.AXIS)
                                    && !blockState.getValue(face.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                    : EncasedCogwheelBlock.BOTTOM_SHAFT));

            /// ENCASED_LARGE_COGWHEEL
            Block encasedLargCogwheelBlock = encasedLargeCogwheel_entry.blocks.get(stoneType);

            registerCTBehviour(encasedLargCogwheelBlock, new EncasedCogCTBehaviour(casingShift));

            CreateClient.CASING_CONNECTIVITY.make(encasedLargCogwheelBlock, casingShift,
                    (blockState, face) ->
                            face.getAxis() == blockState.getValue(EncasedCogwheelBlock.AXIS)
                                    && !blockState.getValue(face.getAxisDirection() == Direction.AxisDirection.POSITIVE ? EncasedCogwheelBlock.TOP_SHAFT
                                    : EncasedCogwheelBlock.BOTTOM_SHAFT));

        });
    }

/*
    public static void registerTunnelCTBehaviour(GemsRealmModule module, SimpleEntrySet<StoneType, Block> tunnelEntry) {
        tunnelEntry.blocks.forEach((stoneType, block) -> {
            String topTextureId = stoneType.createFullIdWith(GemsRealm.MOD_ID, "block", module.shortenedId(), "tunnel/", "tunnel_top");

            CTSpriteShiftEntry tunnelTopShift = vertical(topTextureId);

            registerCTBehviour(block, new CompatTunnelCTBehaviour(tunnelTopShift));
        });
    }
*/

    public static void registerScaffoldCTBehavior(StoneZoneModule module, SimpleEntrySet<StoneType, Block> scaffold) {
        scaffold.blocks.forEach((stoneType, block) -> {
            // Scaffold
            String scaffoldId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold");
            String insideId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "scaffold/", "scaffold_inside");

            // Casing
            String casingId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block", module.shortenedId(), "", "casing");

            CTSpriteShiftEntry scaffoldShift = horizontal(scaffoldId);
            CTSpriteShiftEntry scaffoldInsideShift = horizontal(insideId);
            CTSpriteShiftEntry casingShift = omni(casingId);

            registerCTBehviour(block, new MetalScaffoldingCTBehaviour(scaffoldShift, scaffoldInsideShift, casingShift));

        });
    }

    public static void registerLayeredCTBehavior(CreateModule module) {
        module.layereds.blocks.forEach((stoneType, block) -> {
            String capId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block",
                    module.shortenedId(), "palettes/stone_types/cap/", "cut_cap"
            );
            String layeredId =  stoneType.createFullIdWith(StoneZone.MOD_ID, "block",
                    module.shortenedId(), "palettes/stone_types/layered/", "cut_layered"
            );

            CTSpriteShiftEntry capShift = omni(capId);
            CTSpriteShiftEntry layeredShift = horizontalwithKrypper(layeredId);

            registerCTBehviour(block, new HorizontalCTBehaviour(layeredShift, capShift));
        });
    }

    public static void registerPillarCTBehavior(CreateModule module) {
        module.pillars.blocks.forEach((stoneType, block) -> {
            String pillarId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block",
                    module.shortenedId(), "palettes/stone_types/pillar/", "cut_pillar"
            );
            String capId = stoneType.createFullIdWith(StoneZone.MOD_ID, "block",
                    module.shortenedId(), "palettes/stone_types/cap/", "cut_cap"
            );

            CTSpriteShiftEntry capShift = omni(capId);
            CTSpriteShiftEntry pillarShift = horizontalwithRectangle(pillarId);

            registerCTBehviour(block, new RotatedPillarCTBehaviour(pillarShift, capShift));
        });
    }


    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry omni(String blockTexture) {
        return getCT(AllCTTypes.OMNIDIRECTIONAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture + "_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry horizontal(String blockTexture) {
        return getCT(AllCTTypes.HORIZONTAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture +"_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry horizontalwithKrypper(String blockTexture) {
        return getCT(AllCTTypes.HORIZONTAL_KRYPPERS, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture +"_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry horizontalwithRectangle(String blockTexture) {
        return getCT(AllCTTypes.RECTANGLE, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture +"_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry vertical(String blockTexture) {
        return getCT(AllCTTypes.VERTICAL, ResourceLocation.parse(blockTexture), ResourceLocation.parse(blockTexture + "_connected"));
    }

    ///NOTE: texture filename with _connected is included
    private static CTSpriteShiftEntry roof(String blockTexture, String connectedTexture) {
        return getCT(AllCTTypes.ROOF, ResourceLocation.parse(blockTexture), ResourceLocation.parse(connectedTexture + "_connected"));
    }

    private static void registerCTBehviour(Block entry, ConnectedTextureBehaviour behavior) {
        CreateClient.MODEL_SWAPPER.getCustomBlockModels()
                .register(Utils.getID(entry), model -> new CTModel(model, behavior));
    }
}
