package net.mehvahdjukaar.stone_zone.modules.neoforge.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.logistics.funnel.BeltFunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlock;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlock;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockItem;
import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderScenes;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.createmod.ponder.foundation.PonderIndex;
import net.mehvahdjukaar.every_compat.api.CompatModuleClient;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModuleClient;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModuleAbstract;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

//See CreateModuleAbstract's SUPPORTED VERSION
@SuppressWarnings("CommentedOutCode")
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
    }

    protected Block newCasingBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new CasingBlock(Utils.copyPropertySafe(stoneType.block)
                .sound(stoneType.getSound()));
    }

    @Override
    protected Block newEncasedShaftedBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedShaftBlock(properties, casingBlock);
    }

    @Override
    protected Block newEncasedCogwheelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(properties, false, casingBlock);
    }

    @Override
    protected Block newEncasedLargeCogwheelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(properties, true, casingBlock);
    }

    @Override
    //TODO: Waiting for v26.1.2
    protected Block newFunnelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Supplier<Block> beltFunnelBlock) {
//        Block block = new CompatFunnelBlock(Utils.copyPropertySafe(stoneType.block), beltFunnelBlock);

//        SimpleRegistry.create().register(block, FunnelMovementBehaviour.andesite());

        return null;
    }

    @Override
    protected Block newBeltFunnelBlock(StoneType stoneType, BlockBehaviour.Properties properties, Block funnelBlock) {
        BlockEntry<? extends FunnelBlock> blockEntry = new BlockEntry<>(
                Registrate.create(StoneZone.MOD_ID),
                DeferredBlock.createBlock(Utils.getID(funnelBlock))
        );
        return new BeltFunnelBlock(blockEntry, properties);
    }

//    @Override
//    protected Block newTunnelBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
//        return new BrassTunnelBlock(properties);
//    }

    protected Block makeSlidingDoorBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return SlidingDoorBlock.stone(properties, true);
    }

    protected Block makeMetalLadderBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new MetalLadderBlock(properties);
    }

    @Override
    protected Block makeMetalBarsBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new IronBarsBlock(properties);
    }

    protected Block makeMetalScaffoldingBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new MetalScaffoldingBlock(properties);
    }

    @Override
    protected Block newTableClothBlock(StoneType stoneType, BlockBehaviour.Properties properties) {
        return new TableClothBlock(properties, stoneType.getAppendableId());
    }

    @Override
    protected Item newTableClothBlockItem(Block block, Item.Properties properties) {
        return new TableClothBlockItem(block, properties);
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                          onMod                           │
    //      └──────────────────────────────────────────────────────────┘

    @Override
    public void onModSetup() {
        super.onModSetup();
        putFoldingDoor(this);
        registerEncasedBlock(AllBlocks.SHAFT.get(), encased_shaft);
        registerEncasedBlock(AllBlocks.COGWHEEL.get(), encased_cogwheel);
        registerEncasedBlock(AllBlocks.LARGE_COGWHEEL.get(), encased_large_cogwheel);
    }

//      ┌──────────────────────────────────────────────────────────────────────────────────────────────┐
//      │                                            Client                                            │
//      └──────────────────────────────────────────────────────────────────────────────────────────────┘

    public CompatModuleClient<?> createClient() {
            return new Client();
        }

        private class Client extends StoneZoneModuleClient<CreateModule> {
            private Client() {
                super(CreateModule.this);
            }

        @Override
        public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
            super.registerBlockEntityRenderers(event);
            //TODO: Waiting for v26.1.2
//            event.register(table_cloth.getTile(TableClothBlockEntity.class), TableClothRenderer::new);
//            event.register(door.getTile(SlidingDoorBlockEntity.class), SlidingDoorRenderer::new);
        }

        @Override
        public void onClientInit() {
            super.onClientInit();
            PonderIndex.addPlugin(new CompatCreatePonderPlugin());
        }

        @Override
        @OnlyIn(Dist.CLIENT)
        public void onClientSetup() {
            super.onClientSetup();
            //TODO: waiting for 26.1.2
//            CreateClientModule.registerLayeredCTBehavior(getModule());
//            CreateClientModule.registerPillarCTBehavior(getModule());

//            CreateClientModule.registerCasingCTBehavior(getModule(), casing, encased_shaft, encased_cogwheel, encased_large_cogwheel);
//            CreateClientModule.registerScaffoldCTBehavior(getModule(), scaffolding);
        }

    }

//      ┌──────────────────────────────────────────────────────────┐
//      │                         CLASSES                          │
//      └──────────────────────────────────────────────────────────┘

/*
    public static class CompatFunnelBlock extends FunnelBlock {

        private final Supplier<Block> funnelBlock;

        public CompatFunnelBlock(Properties properties, Supplier<Block> funnelBlock) {
            super(properties);
            this.funnelBlock = funnelBlock;
        }

        @Override
        public BlockState getEquivalentBeltFunnel(BlockGetter world, BlockPos pos, BlockState state) {
            Direction facing = getFacing(state);
            BlockEntry<Block> entry = createStandardBlockEntry(Utils.getID(funnelBlock));

            return entry.getDefaultState()
                    .setValue(BeltFunnelBlock.HORIZONTAL_FACING, facing)
                    .setValue(POWERED, state.getValue(POWERED));
        }
    }
*/

    //TODO: Waiting for v26.1.2
    public class CompatCreatePonderPlugin extends CreatePonderPlugin {

//        @Override
//        public void registerScenes(@NotNull PonderSceneRegistrationHelper<Identifier> helper) {
//            CompatCreatePonderScenes.register(helper, casing, "shaft/encasing", KineticsScenes::shaftsCanBeEncased);
//
//            table_cloth.blocks.values().forEach(block ->
//                    helper.forComponents(Utils.getID(block)).addStoryBoard("high_logistics/table_cloth", TableClothScenes::tableCloth));
//
//            funnel.blocks.values().forEach(block -> {
//                helper.addStoryBoard(Utils.getID(block), "funnels/brass", FunnelScenes::brass);
//                helper.forComponents(Utils.getID(block))
//                        .addStoryBoard("funnels/intro", FunnelScenes::intro, AllCreatePonderTags.LOGISTICS)
//                        .addStoryBoard("funnels/direction", FunnelScenes::directionality)
//                        .addStoryBoard("funnels/compat", FunnelScenes::compat)
//                        .addStoryBoard("funnels/redstone", FunnelScenes::redstone)
//                        .addStoryBoard("funnels/transposer", FunnelScenes::transposer);
//            });
//        }
    }

    public static class CompatCreatePonderScenes extends AllCreatePonderScenes {

        public static void register(PonderSceneRegistrationHelper<Identifier> helper,
                                    SimpleEntrySet<StoneType, ? extends Block> entrySet,
                                    String schematicPath,
                                    PonderStoryBoard ponderStoryBoard) {

//            PonderSceneRegistrationHelper<ItemProviderEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);
//
//            entrySet.blocks.values().forEach(block -> {
//
//                DeferredItem<Item> itemHolder = DeferredItem.createItem(Utils.getID(block));
//
//                ItemProviderEntry<Item, Item> itemEntry = new ItemProviderEntry<>(Registrate.create(StoneZone.MOD_ID), itemHolder);
//
//                HELPER.addStoryBoard(itemEntry, schematicPath, ponderStoryBoard);
//            });

        }

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         Utitiles                         │
    //      └──────────────────────────────────────────────────────────┘

    protected static BlockEntry<Block> createStandardBlockEntry(Identifier blockId) {
        return new BlockEntry<>(Registrate.create(StoneZone.MOD_ID), DeferredBlock.createBlock(blockId));
    }

    //TODO: Waiting for v26.1.2
    protected void putFoldingDoor(CreateModuleAbstract module) {
        module.door.blocks.forEach((stoneType, block) -> {
            String path = stoneType.createPathWith(module.shortenedId(), "door");
//            FOLDING_DOORS.put(StoneZone.res(path),
//                    Couple.create(block(path + "/fold_left"), block(path + "/fold_right")));
        });
    }

    //TODO: Waiting for v26.1.2
//    private static PartialModel block(String path) {
//        return PartialModel.of(StoneZone.res("block/"+ path));
//    }
}
