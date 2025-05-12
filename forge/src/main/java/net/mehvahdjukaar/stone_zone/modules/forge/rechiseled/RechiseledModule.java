package net.mehvahdjukaar.stone_zone.modules.forge.rechiseled;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.supermartijn642.core.TextComponents;
import com.supermartijn642.core.block.BaseBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.TextureInfo;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.StoneZoneModule;
import net.mehvahdjukaar.stone_zone.api.set.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

import static com.supermartijn642.rechiseled.blocks.RechiseledPillarBlock.AXIS_PROPERTY;

//SUPPORT: v1.1.6+
//NOTE: There is a difference between FORGE & FABRIC - the key class is BlockState via RechiseledPillarBlock
public class RechiseledModule extends StoneZoneModule {

    public final SimpleEntrySet<StoneType, Block> big_tiles, big_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> bordered, bordered_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_pattern, brick_pattern_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_paving, brick_paving_connecting;
    public final SimpleEntrySet<StoneType, Block> crushed, crushed_connecting;
    public final SimpleEntrySet<StoneType, Block> diagonal_bricks, diagonal_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> path, path_connecting;
    public final SimpleEntrySet<StoneType, Block> rotated_bricks, rotated_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> small_bricks, small_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> small_tiles, small_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_brick_paving, smooth_brick_paving_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_large_tiles, smooth_large_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_rotated_bricks, smooth_rotated_bricks_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_tiles, smooth_tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> squares, squares_connecting;
    public final SimpleEntrySet<StoneType, Block> waves, waves_connecting;
    public final SimpleEntrySet<StoneType, Block> tiles, tiles_connecting;
    public final SimpleEntrySet<StoneType, Block> slated;

    /* Skip them for now.
     * minecraft:smooth_stone
     * rechiseled:stone_smooth_connecting
     * minecraft:chiseled_stone_bricks
     * rechiseled:stone_chiseled_bricks_connecting
    */

    public RechiseledModule(String modId) {
        super(modId, "rcd");
        ResourceLocation tab = modRes(modId);

        big_tiles = StoneZoneEntrySet.of(StoneType.class, "big_tiles",
                        getModBlock("stone_big_tiles"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_big_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles);

        big_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "big_tiles_connecting",
                        getModBlock("stone_big_tiles_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles_connecting);

        bordered = StoneZoneEntrySet.of(StoneType.class, "bordered",
                        getModBlock("stone_bordered"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_bordered")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered);

        bordered_connecting = StoneZoneEntrySet.of(StoneType.class, "bordered_connecting",
                        getModBlock("stone_bordered_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered_connecting);

        brick_pattern = StoneZoneEntrySet.of(StoneType.class, "brick_pattern",
                        getModBlock("stone_brick_pattern"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_brick_pattern")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern);

        brick_pattern_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_connecting",
                        getModBlock("stone_brick_pattern_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern_connecting);

        brick_paving = StoneZoneEntrySet.of(StoneType.class, "brick_paving",
                        getModBlock("stone_brick_paving"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_brick_paving")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving);

        brick_paving_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_paving_connecting",
                        getModBlock("stone_brick_paving_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving_connecting);

        crushed = StoneZoneEntrySet.of(StoneType.class, "crushed",
                        getModBlock("stone_crushed"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_crushed")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed);

        crushed_connecting = StoneZoneEntrySet.of(StoneType.class, "crushed_connecting",
                        getModBlock("stone_crushed_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed_connecting);

        diagonal_bricks = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks",
                        getModBlock("stone_diagonal_bricks"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_diagonal_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks);

        diagonal_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_connecting",
                        getModBlock("stone_diagonal_bricks_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks_connecting);

        path = StoneZoneEntrySet.of(StoneType.class, "path",
                        getModBlock("stone_path"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_path")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path);

        path_connecting = StoneZoneEntrySet.of(StoneType.class, "path_connecting",
                        getModBlock("stone_path_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path_connecting);

        rotated_bricks = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks",
                        getModBlock("stone_rotated_bricks"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_rotated_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks);

        rotated_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_connecting",
                        getModBlock("stone_rotated_bricks_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks_connecting);

        small_bricks = StoneZoneEntrySet.of(StoneType.class, "small_bricks",
                        getModBlock("stone_small_bricks"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_small_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks);

        small_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "small_bricks_connecting",
                        getModBlock("stone_small_bricks_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks_connecting);

        small_tiles = StoneZoneEntrySet.of(StoneType.class, "small_tiles",
                        getModBlock("stone_small_tiles"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_small_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles);

        small_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "small_tiles_connecting",
                        getModBlock("stone_small_tiles_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles_connecting);

        smooth_brick_paving = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving",
                        getModBlock("stone_smooth_brick_paving"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_brick_paving")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving);

        smooth_brick_paving_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_connecting",
                        getModBlock("stone_smooth_brick_paving_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving_connecting);

        smooth_large_tiles = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles",
                        getModBlock("stone_smooth_large_tiles"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_large_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles);

        smooth_large_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_connecting",
                        getModBlock("stone_smooth_large_tiles_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles_connecting);

        smooth_rotated_bricks = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks",
                        getModBlock("stone_smooth_rotated_bricks"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_rotated_bricks")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks);

        smooth_rotated_bricks_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_connecting",
                        getModBlock("stone_smooth_rotated_bricks_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_connecting);

        smooth_tiles = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles",
                        getModBlock("stone_smooth_tiles"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_smooth_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles);

        smooth_tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_connecting",
                        getModBlock("stone_smooth_tiles_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles_connecting);

        squares = StoneZoneEntrySet.of(StoneType.class, "squares",
                        getModBlock("stone_squares"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_squares")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares);

        squares_connecting = StoneZoneEntrySet.of(StoneType.class, "squares_connecting",
                        getModBlock("stone_squares_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares_connecting);

        waves = StoneZoneEntrySet.of(StoneType.class, "waves",
                        getModBlock("stone_waves"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_waves")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves);

        waves_connecting = StoneZoneEntrySet.of(StoneType.class, "waves_connecting",
                        getModBlock("stone_waves_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves_connecting);

        tiles = StoneZoneEntrySet.of(StoneType.class, "tiles",
                        getModBlock("stone_tiles"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(TextureInfo.of(modRes("block/stone_tiles")).copyMCMETA())
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles);

        tiles_connecting = StoneZoneEntrySet.of(StoneType.class, "tiles_connecting",
                        getModBlock("stone_tiles_connecting"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledBlock(true, Utils.copyPropertySafe(type.stone))
                )
                .requiresFromMap(tiles.blocks)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles_connecting);

        slated = StoneZoneEntrySet.of(StoneType.class, "slated",
                        getModBlock("stone_slated"), StoneTypeRegistry::getStoneType,
                        type -> new CompatRechiseledPillarBlock(false, Utils.copyPropertySafe(type.stone))
                )
                .addTexture(modRes("block/stone_slated_end"))
                .addTexture(modRes("block/stone_slated_side"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(slated);

    }

    /// CUSTOM CLASS - REASON: Changed BlockProperties to Proerties to use Utils.copyPropertySafe()
    public static class CompatRechiseledBlock extends BaseBlock {
        public final boolean connecting;

        public CompatRechiseledBlock(boolean connecting, BlockBehaviour.Properties properties) {
            super(false, properties);
            this.connecting = connecting;
        }

        protected void appendItemInformation(ItemStack stack, @Nullable BlockGetter level, Consumer<Component> info, boolean advanced) {
            if (this.connecting) {
                info.accept(TextComponents.translation("rechiseled.tooltip.connecting").color(ChatFormatting.GRAY).get());
            }

        }
    }

    public static class CompatRechiseledPillarBlock extends CompatRechiseledBlock {
        public CompatRechiseledPillarBlock(boolean connecting, BlockBehaviour.Properties properties) {
            super(connecting, properties);
            this.registerDefaultState(this.defaultBlockState().setValue(AXIS_PROPERTY, Direction.Axis.Y));
        }

        @Nullable
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context){
            return this.defaultBlockState().setValue(AXIS_PROPERTY, context.getClickedFace().getAxis());
        }

        @Override
        @SuppressWarnings("deprecation")
        public @NotNull BlockState rotate(@NotNull BlockState state, @NotNull Rotation rotation){
            if(rotation == Rotation.CLOCKWISE_90 || rotation == Rotation.COUNTERCLOCKWISE_90){
                Direction.Axis axis = state.getValue(AXIS_PROPERTY);
                if(axis != Direction.Axis.Y)
                    return state.setValue(AXIS_PROPERTY, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
            }
            return state;
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
            builder.add(AXIS_PROPERTY);
        }
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) ->

            big_tiles.blocks.forEach((stoneType, block) -> {

                // Adding all supported-blocks of a StoneType to Array
                JsonArray entriesArray = new JsonArray();

                for (var entry : this.getEntries()) {
                    JsonObject entryObj = new JsonObject();

                    SimpleEntrySet<?, ?> currentEntry = ((SimpleEntrySet<?, ?>) entry);
                    String currentName = currentEntry.getName();

                    // Get the other block with "_connecting"
                    String entryKey = currentName + "_connecting";

                    if (!currentName.contains("_connecting")) { // Skip the blocks with "_connecting"
                        Block currentBlock = currentEntry.blocks.get(stoneType);
                        if (Objects.nonNull(currentBlock)) {
                            entryObj.addProperty("item", Utils.getID(currentBlock).toString());

                            if (!currentName.matches("slated")) { // Skip blc it don't have "_connecting" block
                                StoneZoneEntrySet<?, ?> otherEntry = (StoneZoneEntrySet<?, ?>) this.getEntry(entryKey);
                                Block otherBlock = otherEntry.blocks.get(stoneType);
                                if (Objects.nonNull(otherBlock))
                                    entryObj.addProperty("connecting_item", Utils.getID(otherBlock).toString());
                            }

                            entriesArray.add(entryObj);
                        }
                    }
                }

                // Adding vanilla blocks to Array
                String[] vanillaBlocks = {
                        "stone",
                        "bricks",
                        "mossy_bricks",
                        "cracked_bricks",
                        "smooth"
                };

                for (var key : vanillaBlocks) {
                    Block currentBlock = stoneType.getBlockOfThis(key);
                    if (Objects.nonNull(currentBlock)) {
                        JsonObject entryObj = new JsonObject();
                        entryObj.addProperty("item", Utils.getID(currentBlock).toString());
                        entriesArray.add(entryObj);
                    }
                }

                // Recipes
                JsonObject chiseling_recipe = new JsonObject();
                chiseling_recipe.addProperty("type", "rechiseled:chiseling");
                chiseling_recipe.addProperty("overwrite", false);
                chiseling_recipe.add("entries", entriesArray);

                // Adding to resources
                ResourceLocation resLoc = StoneZone.res("chiseling_recipes/" + stoneType.getAppendableId());
                sink.addJson(resLoc, chiseling_recipe, ResType.JSON);

            })
        );
    }
}