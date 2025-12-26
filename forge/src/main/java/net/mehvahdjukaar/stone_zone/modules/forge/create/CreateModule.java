package net.mehvahdjukaar.stone_zone.modules.forge.create;

import com.simibubi.create.CreateClient;
import com.simibubi.create.content.decoration.palettes.ConnectedPillarBlock;
import com.simibubi.create.content.decoration.palettes.LayeredBlock;
import com.simibubi.create.foundation.block.connected.*;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModuleAbstract;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Objects;

//See CreateModuleAbstract's SUPPORTED VERSION
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
    }

    @Override
    public Block newConnectedPillarBlock(StoneType stoneType) {
        return new ConnectedPillarBlock(Utils.copyPropertySafe(stoneType.stone));
    }

    @Override
    public Block newLayeredBlock(StoneType stoneType) {
        return new LayeredBlock(Utils.copyPropertySafe(stoneType.stone));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerConnectedBlock(this);
    }

    @OnlyIn(Dist.CLIENT)
    private static class CreateClientModule {
        private static void registerConnectedBlock(CreateModule module) {
            module.layereds.blocks.forEach((stone, block) -> {
                String capPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/cap/" + stone.getTypeName() + "_cut_cap";
                String layeredPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/layered/" + stone.getTypeName() + "_cut_layered";

                CTSpriteShiftEntry capShift = CTSpriteShifter.getCT(AllCTTypes.OMNIDIRECTIONAL,
                        StoneZone.res(capPath), StoneZone.res(capPath + "_connected"));
                CTSpriteShiftEntry layeredShift = CTSpriteShifter.getCT(AllCTTypes.HORIZONTAL_KRYPPERS,
                        StoneZone.res(layeredPath), StoneZone.res(layeredPath + "_connected"));

                Block pillarBlock = module.pillars.blocks.get(stone);
                if (Objects.nonNull(pillarBlock)) {
                    String pillarPath = "block/" + module.shortenedId() + "/" + stone.getNamespace() + "/palettes/stone_types/pillar/" + stone.getTypeName() + "_cut_pillar";

                    CTSpriteShiftEntry pillarShift = CTSpriteShifter.getCT(AllCTTypes.RECTANGLE,
                            StoneZone.res(pillarPath), StoneZone.res(pillarPath + "_connected"));

                    CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(pillarBlock),
                            (model) -> new CTModel(model, new RotatedPillarCTBehaviour(pillarShift, capShift)));
                }

                CreateClient.MODEL_SWAPPER.getCustomBlockModels().register(Utils.getID(block),
                        (model) -> new CTModel(model, new HorizontalCTBehaviour(layeredShift, capShift)));

            });
        }
    }
}