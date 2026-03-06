package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledSlabBlock;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.api.StoneZoneEntrySet;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.api.set.stone.VanillaStoneTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.Objects;

// See RechiseledModuleAbstract's Supported Version
public class RechiseledModuleSlab extends RechiseledModuleAbstract {

    public final SimpleEntrySet<StoneType, Block> big_tiles_slab, big_tiles_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> bordered_slab, bordered_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_pattern_slab, brick_pattern_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_paving_slab, brick_paving_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> crushed_slab, crushed_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> diagonal_bricks_slab, diagonal_bricks_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> path_slab, path_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> rotated_bricks_slab, rotated_bricks_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> small_bricks_slab, small_bricks_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> small_tiles_slab, small_tiles_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_brick_paving_slab, smooth_brick_paving_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_large_tiles_slab, smooth_large_tiles_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_rotated_bricks_slab, smooth_rotated_bricks_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_tiles_slab, smooth_tiles_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> squares_slab, squares_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> waves_slab, waves_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> tiles_slab, tiles_slab_connecting;
    public final SimpleEntrySet<StoneType, Block> slated_slab;

    /* Skip them for now.
     * minecraft:smooth_stone
     * rechiseled:stone_smooth_connecting
     * minecraft:chiseled_stone_bricks
     * rechiseled:stone_chiseled_bricks_connecting
    */

    public RechiseledModuleSlab(String modId) {
        super(modId);

        big_tiles_slab = StoneZoneEntrySet.of(StoneType.class, "big_tiles_slab",
                        getModBlock("stone_big_tiles_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "big_tiles")))
                //TEXTURES: big_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles_slab);

        big_tiles_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "big_tiles_slab_connecting",
                        getModBlock("stone_big_tiles_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "big_tiles_connecting")))
                //TEXTURES: big_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(big_tiles_slab_connecting);

        bordered_slab = StoneZoneEntrySet.of(StoneType.class, "bordered_slab",
                        getModBlock("stone_bordered_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "bordered")))
                //TEXTURES: bordered @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered_slab);

        bordered_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "bordered_slab_connecting",
                        getModBlock("stone_bordered_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "bordered_connecting")))
                //TEXTURES: bordered @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(bordered_slab_connecting);

        brick_pattern_slab = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_slab",
                        getModBlock("stone_brick_pattern_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_pattern")))
                //TEXTURES: brick_pattern @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern_slab);

        brick_pattern_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_slab_connecting",
                        getModBlock("stone_brick_pattern_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_pattern_connecting")))
                //TEXTURES: brick_pattern @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_pattern_slab_connecting);

        brick_paving_slab = StoneZoneEntrySet.of(StoneType.class, "brick_paving_slab",
                        getModBlock("stone_brick_paving_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_paving")))
                //TEXTURES: brick_paving @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving_slab);

        brick_paving_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_paving_slab_connecting",
                        getModBlock("stone_brick_paving_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_paving_connecting")))
                //TEXTURES: brick_paving @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(brick_paving_slab_connecting);

        crushed_slab = StoneZoneEntrySet.of(StoneType.class, "crushed_slab",
                        getModBlock("stone_crushed_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "crushed")))
                //TEXTURES: crushed @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed_slab);

        crushed_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "crushed_slab_connecting",
                        getModBlock("stone_crushed_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "crushed_connecting")))
                //TEXTURES: crushed @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(crushed_slab_connecting);

        diagonal_bricks_slab = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_slab",
                        getModBlock("stone_diagonal_bricks_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "diagonal_bricks")))
                //TEXTURES: diagonal_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks_slab);

        diagonal_bricks_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_slab_connecting",
                        getModBlock("stone_diagonal_bricks_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "diagonal_bricks_connecting")))
                //TEXTURES: diagonal_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(diagonal_bricks_slab_connecting);

        path_slab = StoneZoneEntrySet.of(StoneType.class, "path_slab",
                        getModBlock("stone_path_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "path")))
                //TEXTURES: path @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path_slab);

        path_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "path_slab_connecting",
                        getModBlock("stone_path_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "path_connecting")))
                //TEXTURES: path @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(path_slab_connecting);

        rotated_bricks_slab = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_slab",
                        getModBlock("stone_rotated_bricks_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "rotated_bricks")))
                //TEXTURES: rotated_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks_slab);

        rotated_bricks_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_slab_connecting",
                        getModBlock("stone_rotated_bricks_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "rotated_bricks_connecting")))
                //TEXTURES: rotated_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(rotated_bricks_slab_connecting);

        small_bricks_slab = StoneZoneEntrySet.of(StoneType.class, "small_bricks_slab",
                        getModBlock("stone_small_bricks_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_bricks")))
                //TEXTURES: small_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks_slab);

        small_bricks_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "small_bricks_slab_connecting",
                        getModBlock("stone_small_bricks_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_bricks_connecting")))
                //TEXTURES: small_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_bricks_slab_connecting);

        small_tiles_slab = StoneZoneEntrySet.of(StoneType.class, "small_tiles_slab",
                        getModBlock("stone_small_tiles_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_tiles")))
                //TEXTURES: small_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles_slab);

        small_tiles_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "small_tiles_slab_connecting",
                        getModBlock("stone_small_tiles_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_tiles_connecting")))
                //TEXTURES: small_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(small_tiles_slab_connecting);

        smooth_brick_paving_slab = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_slab",
                        getModBlock("stone_smooth_brick_paving_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_brick_paving")))
                //TEXTURES: smooth_brick_paving @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving_slab);

        smooth_brick_paving_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_slab_connecting",
                        getModBlock("stone_smooth_brick_paving_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_brick_paving_connecting")))
                //TEXTURES: smooth_brick_paving @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_brick_paving_slab_connecting);

        smooth_large_tiles_slab = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_slab",
                        getModBlock("stone_smooth_large_tiles_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_large_tiles")))
                //TEXTURES: smooth_large_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles_slab);

        smooth_large_tiles_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_slab_connecting",
                        getModBlock("stone_smooth_large_tiles_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_large_tiles_connecting")))
                //TEXTURES: smooth_large_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_large_tiles_slab_connecting);

        smooth_rotated_bricks_slab = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_slab",
                        getModBlock("stone_smooth_rotated_bricks_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_rotated_bricks")))
                //TEXTURES: smooth_rotated_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_slab);

        smooth_rotated_bricks_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_slab_connecting",
                        getModBlock("stone_smooth_rotated_bricks_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_rotated_bricks_connecting")))
                //TEXTURES: smooth_rotated_bricks @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_slab_connecting);

        smooth_tiles_slab = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_slab",
                        getModBlock("stone_smooth_tiles_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_tiles")))
                //TEXTURES: smooth_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles_slab);

        smooth_tiles_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_slab_connecting",
                        getModBlock("stone_smooth_tiles_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_tiles_connecting")))
                //TEXTURES: smooth_tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(smooth_tiles_slab_connecting);

        squares_slab = StoneZoneEntrySet.of(StoneType.class, "squares_slab",
                        getModBlock("stone_squares_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "squares")))
                //TEXTURES: squares @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares_slab);

        squares_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "squares_slab_connecting",
                        getModBlock("stone_squares_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "squares_connecting")))
                //TEXTURES: squares @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(squares_slab_connecting);

        waves_slab = StoneZoneEntrySet.of(StoneType.class, "waves_slab",
                        getModBlock("stone_waves_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "waves")))
                //TEXTURES: waves @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves_slab);

        waves_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "waves_slab_connecting",
                        getModBlock("stone_waves_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "waves_connecting")))
                //TEXTURES: waves @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(waves_slab_connecting);

        tiles_slab = StoneZoneEntrySet.of(StoneType.class, "tiles_slab",
                        getModBlock("stone_tiles_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "tiles")))
                //TEXTURES: tiles @ RechiseledModule
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles_slab);

        tiles_slab_connecting = StoneZoneEntrySet.of(StoneType.class, "tiles_slab_connecting",
                        getModBlock("stone_tiles_slab_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(true, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "tiles_connecting")))
                //TEXTURES: tiles @ RechiseledModule
                .requiresFromMap(tiles_slab.blocks)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(tiles_slab_connecting);

        slated_slab = StoneZoneEntrySet.of(StoneType.class, "slated_slab",
                        getModBlock("stone_slated_slab"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledSlabBlock(false, Utils.copyPropertySafe(type.stone))
                )
                //REASON: slab's parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "slated")))
                //TEXTURES: stone_slated_end,
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTabKey(tab)
                .build();
        this.addEntry(slated_slab);

    }

}