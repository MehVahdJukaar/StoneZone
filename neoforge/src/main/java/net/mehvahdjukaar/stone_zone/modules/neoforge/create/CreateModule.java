package net.mehvahdjukaar.stone_zone.modules.neoforge.create;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.api.registry.SimpleRegistry;
import com.simibubi.create.content.decoration.MetalLadderBlock;
import com.simibubi.create.content.decoration.MetalScaffoldingBlock;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedCogwheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.encased.EncasedShaftBlock;
import com.simibubi.create.content.logistics.funnel.BeltFunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelMovementBehaviour;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlock;
import com.simibubi.create.content.logistics.tableCloth.TableClothBlockItem;
import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderScenes;
import com.simibubi.create.infrastructure.ponder.AllCreatePonderTags;
import com.simibubi.create.infrastructure.ponder.scenes.FunnelScenes;
import com.simibubi.create.infrastructure.ponder.scenes.KineticsScenes;
import com.simibubi.create.infrastructure.ponder.scenes.highLogistics.TableClothScenes;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.PonderStoryBoard;
import net.createmod.ponder.foundation.PonderIndex;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.modules.create.CreateModuleAbstract;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

//See CreateModuleAbstract's SUPPORTED VERSION
@SuppressWarnings("CommentedOutCode")
public class CreateModule extends CreateModuleAbstract {

    public CreateModule(String modId) {
        super(modId);
    }

    protected Block newCasingBlock(StoneType stoneType) {
        return new CasingBlock(Utils.copyPropertySafe(stoneType.block)
                .sound(stoneType.getSound()));
    }

    @Override
    protected Block newEncasedShaftedBlock(StoneType stoneType, Supplier<Block> casingBlock) {
        return new EncasedShaftBlock(Utils.copyPropertySafe(stoneType.block).noOcclusion(), casingBlock);
    }

    @Override
    protected Block newEncasedCogwheelBlock(StoneType stoneType, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(Utils.copyPropertySafe(stoneType.block).noOcclusion(), false, casingBlock);
    }

    @Override
    protected Block newEncasedLargeCogwheelBlock(StoneType stoneType, Supplier<Block> casingBlock) {
        return new EncasedCogwheelBlock(Utils.copyPropertySafe(stoneType.block).noOcclusion(), true, casingBlock);
    }

    @Override
    protected Block newFunnelBlock(StoneType stoneType, Supplier<Block> beltFunnelBlock) {
        Block block = new CompatFunnelBlock(Utils.copyPropertySafe(stoneType.block), beltFunnelBlock);

        SimpleRegistry.create().register(block, FunnelMovementBehaviour.andesite());

        return block;
    }

    @Override
    protected Block newBeltFunnelBlock(StoneType stoneType, Block funnelBlock) {
        BlockEntry<? extends FunnelBlock> blockEntry = new BlockEntry<>(
                Registrate.create(StoneZone.MOD_ID),
                DeferredBlock.createBlock(Utils.getID(funnelBlock))
        );
        return new BeltFunnelBlock(blockEntry, Utils.copyPropertySafe(stoneType.block));
    }

//    @Override
//    protected Block newTunnelBlock(StoneType stoneType) {
//        return new BrassTunnelBlock(Utils.copyPropertySafe(stoneType.block).noOcclusion());
//    }

    protected Block makeSlidingDoorBlock(StoneType stoneType) {
        return SlidingDoorBlock.stone(Utils.copyPropertySafe(stoneType.block)
                        .sound(stoneType.getSound())
                        .noOcclusion(),
                true);
    }

    protected Block makeMetalLadderBlock(StoneType stoneType) {
        return new MetalLadderBlock(Utils.copyPropertySafe(stoneType.block));
    }

    @Override
    protected Block makeMetalBarsBlock(StoneType stoneType) {
        return new IronBarsBlock(Utils.copyPropertySafe(Blocks.IRON_BARS)
                .sound(SoundType.COPPER)
                .mapColor(stoneType.stone.defaultMapColor())
        );
    }

    protected Block makeMetalScaffoldingBlock(StoneType stoneType) {
        return new MetalScaffoldingBlock(Utils.copyPropertySafe(stoneType.block));
    }

    @Override
    protected Block newTableClothBlock(StoneType stoneType) {
        return new TableClothBlock(Utils.copyPropertySafe(stoneType.block), stoneType.getTypeName());
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
        registerEncasedBlock(AllBlocks.SHAFT.get(), encased_shaft);
        registerEncasedBlock(AllBlocks.COGWHEEL.get(), encased_cogwheel);
        registerEncasedBlock(AllBlocks.LARGE_COGWHEEL.get(), encased_large_cogwheel);
    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         OnClient                         │
    //      └──────────────────────────────────────────────────────────┘

    @Override
    public void onClientInit() {
        super.onClientInit();
        PonderIndex.addPlugin(new CompatCreatePonderPlugin());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onClientSetup() {
        super.onClientSetup();
        CreateClientModule.registerLayeredCTBehavior(this);
        CreateClientModule.registerPillarCTBehavior(this);

        CreateClientModule.registerCasingCTBehavior(this, casing, encased_shaft, encased_cogwheel, encased_large_cogwheel);
        CreateClientModule.registerScaffoldCTBehavior(this, scaffolding);
    }


//      ┌──────────────────────────────────────────────────────────┐
//      │                         CLASSES                          │
//      └──────────────────────────────────────────────────────────┘

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

    public class CompatCreatePonderPlugin extends CreatePonderPlugin {
        @Override
        public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
            CompatCreatePonderScenes.register(helper, casing, "shaft/encasing", KineticsScenes::shaftsCanBeEncased);

            table_cloth.blocks.values().forEach(block ->
                    helper.forComponents(Utils.getID(block)).addStoryBoard("high_logistics/table_cloth", TableClothScenes::tableCloth));

            funnel.blocks.values().forEach(block -> {
                helper.addStoryBoard(Utils.getID(block), "funnels/brass", FunnelScenes::brass);
                helper.forComponents(Utils.getID(block))
                        .addStoryBoard("funnels/intro", FunnelScenes::intro, AllCreatePonderTags.LOGISTICS)
                        .addStoryBoard("funnels/direction", FunnelScenes::directionality)
                        .addStoryBoard("funnels/compat", FunnelScenes::compat)
                        .addStoryBoard("funnels/redstone", FunnelScenes::redstone)
                        .addStoryBoard("funnels/transposer", FunnelScenes::transposer);
            });
        }
    }

    public static class CompatCreatePonderScenes extends AllCreatePonderScenes {

        public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper,
                                    SimpleEntrySet<StoneType, ? extends Block> entrySet,
                                    String schematicPath,
                                    PonderStoryBoard ponderStoryBoard) {

            PonderSceneRegistrationHelper<ItemProviderEntry<?,?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

            entrySet.blocks.values().forEach(block -> {

                DeferredItem<Item> itemHolder = DeferredItem.createItem(Utils.getID(block));

                ItemProviderEntry<Item, Item> itemEntry = new ItemProviderEntry<>(Registrate.create(StoneZone.MOD_ID), itemHolder);

                HELPER.addStoryBoard(itemEntry, schematicPath, ponderStoryBoard);
            });

        }

    }

    //      ┌──────────────────────────────────────────────────────────┐
    //      │                         Utitiles                         │
    //      └──────────────────────────────────────────────────────────┘

    protected static BlockEntry<Block> createStandardBlockEntry(ResourceLocation blockId) {
        return new BlockEntry<>(Registrate.create(StoneZone.MOD_ID), DeferredBlock.createBlock(blockId));
    }
}