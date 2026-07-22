package net.mehvahdjukaar.stone_zone.modules.rechiseled;

import com.supermartijn642.rechiseled.blocks.RechiseledStairBlock;
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
public class RechiseledModuleStairs extends RechiseledModuleAbstract {

    public final SimpleEntrySet<StoneType, Block> big_tiles_stairs, big_tiles_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> bordered_stairs, bordered_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_pattern_stairs, brick_pattern_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> brick_paving_stairs, brick_paving_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> crushed_stairs, crushed_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> diagonal_bricks_stairs, diagonal_bricks_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> path_stairs, path_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> rotated_bricks_stairs, rotated_bricks_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> small_bricks_stairs, small_bricks_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> small_tiles_stairs, small_tiles_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_brick_paving_stairs, smooth_brick_paving_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_large_tiles_stairs, smooth_large_tiles_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_rotated_bricks_stairs, smooth_rotated_bricks_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> smooth_tiles_stairs, smooth_tiles_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> squares_stairs, squares_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> waves_stairs, waves_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> tiles_stairs, tiles_stairs_connecting;
    public final SimpleEntrySet<StoneType, Block> slated_stairs;

    /* Skip them for now.
     * minecraft:smooth_stone
     * rechiseled:stone_smooth_connecting
     * minecraft:chiseled_stone_bricks
     * rechiseled:stone_chiseled_bricks_connecting
    */

    public RechiseledModuleStairs(String modId) {
        super(modId);

        big_tiles_stairs = StoneZoneEntrySet.of(StoneType.class, "big_tiles_stairs",
                        getModBlock("stone_big_tiles_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "big_tiles").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "big_tiles")))
                //TEXTURES: stone_big_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(big_tiles_stairs);

        big_tiles_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "big_tiles_stairs_connecting",
                        getModBlock("stone_big_tiles_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "big_tiles_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "big_tiles_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(big_tiles_stairs_connecting);

        bordered_stairs = StoneZoneEntrySet.of(StoneType.class, "bordered_stairs",
                        getModBlock("stone_bordered_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "bordered").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "bordered")))
                //TEXTURES: stone_bordered
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(bordered_stairs);

        bordered_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "bordered_stairs_connecting",
                        getModBlock("stone_bordered_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "bordered_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "bordered_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(bordered_stairs_connecting);

        brick_pattern_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_stairs",
                        getModBlock("stone_brick_pattern_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "brick_pattern").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_pattern")))
                //TEXTURES: stone_brick_pattern
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(brick_pattern_stairs);

        brick_pattern_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_pattern_stairs_connecting",
                        getModBlock("stone_brick_pattern_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "brick_pattern_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_pattern_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(brick_pattern_stairs_connecting);

        brick_paving_stairs = StoneZoneEntrySet.of(StoneType.class, "brick_paving_stairs",
                        getModBlock("stone_brick_paving_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "brick_paving").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_paving")))
                //TEXTURES: stone_brick_paving
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(brick_paving_stairs);

        brick_paving_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "brick_paving_stairs_connecting",
                        getModBlock("stone_brick_paving_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "brick_paving_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "brick_paving_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(brick_paving_stairs_connecting);

        crushed_stairs = StoneZoneEntrySet.of(StoneType.class, "crushed_stairs",
                        getModBlock("stone_crushed_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "crushed").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "crushed")))
                //TEXTURES: stone_crushed
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(crushed_stairs);

        crushed_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "crushed_stairs_connecting",
                        getModBlock("stone_crushed_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "crushed_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "crushed_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(crushed_stairs_connecting);

        diagonal_bricks_stairs = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_stairs",
                        getModBlock("stone_diagonal_bricks_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "diagonal_bricks").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "diagonal_bricks")))
                //TEXTURES: stone_diagonal_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(diagonal_bricks_stairs);

        diagonal_bricks_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "diagonal_bricks_stairs_connecting",
                        getModBlock("stone_diagonal_bricks_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "diagonal_bricks_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "diagonal_bricks_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(diagonal_bricks_stairs_connecting);

        path_stairs = StoneZoneEntrySet.of(StoneType.class, "path_stairs",
                        getModBlock("stone_path_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "path").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "path")))
                //TEXTURES: stone_path
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(path_stairs);

        path_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "path_stairs_connecting",
                        getModBlock("stone_path_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "path_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "path_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(path_stairs_connecting);

        rotated_bricks_stairs = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_stairs",
                        getModBlock("stone_rotated_bricks_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "rotated_bricks").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "rotated_bricks")))
                //TEXTURES: stone_rotated_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(rotated_bricks_stairs);

        rotated_bricks_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "rotated_bricks_stairs_connecting",
                        getModBlock("stone_rotated_bricks_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "rotated_bricks_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "rotated_bricks_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(rotated_bricks_stairs_connecting);

        small_bricks_stairs = StoneZoneEntrySet.of(StoneType.class, "small_bricks_stairs",
                        getModBlock("stone_small_bricks_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "small_bricks").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_bricks")))
                //TEXTURES: stone_small_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(small_bricks_stairs);

        small_bricks_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "small_bricks_stairs_connecting",
                        getModBlock("stone_small_bricks_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "small_bricks_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_bricks_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(small_bricks_stairs_connecting);

        small_tiles_stairs = StoneZoneEntrySet.of(StoneType.class, "small_tiles_stairs",
                        getModBlock("stone_small_tiles_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "small_tiles").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_tiles")))
                //TEXTURES: stone_small_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(small_tiles_stairs);

        small_tiles_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "small_tiles_stairs_connecting",
                        getModBlock("stone_small_tiles_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "small_tiles_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "small_tiles_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(small_tiles_stairs_connecting);

        smooth_brick_paving_stairs = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_stairs",
                        getModBlock("stone_smooth_brick_paving_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "smooth_brick_paving").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_brick_paving")))
                //TEXTURES: stone_smooth_brick_paving
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_brick_paving_stairs);

        smooth_brick_paving_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_brick_paving_stairs_connecting",
                        getModBlock("stone_smooth_brick_paving_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "smooth_brick_paving_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_brick_paving_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_brick_paving_stairs_connecting);

        smooth_large_tiles_stairs = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_stairs",
                        getModBlock("stone_smooth_large_tiles_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "smooth_large_tiles").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_large_tiles")))
                //TEXTURES: stone_smooth_large_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_large_tiles_stairs);

        smooth_large_tiles_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_large_tiles_stairs_connecting",
                        getModBlock("stone_smooth_large_tiles_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "smooth_large_tiles_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_large_tiles_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_large_tiles_stairs_connecting);

        smooth_rotated_bricks_stairs = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_stairs",
                        getModBlock("stone_smooth_rotated_bricks_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "smooth_rotated_bricks").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_rotated_bricks")))
                //TEXTURES: stone_smooth_rotated_bricks
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_stairs);

        smooth_rotated_bricks_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_rotated_bricks_stairs_connecting",
                        getModBlock("stone_smooth_rotated_bricks_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "smooth_rotated_bricks_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_rotated_bricks_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_rotated_bricks_stairs_connecting);

        smooth_tiles_stairs = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_stairs",
                        getModBlock("stone_smooth_tiles_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "smooth_tiles").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_tiles")))
                //TEXTURES: stone_smooth_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_tiles_stairs);

        smooth_tiles_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "smooth_tiles_stairs_connecting",
                        getModBlock("stone_smooth_tiles_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "smooth_tiles_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "smooth_tiles_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(smooth_tiles_stairs_connecting);

        squares_stairs = StoneZoneEntrySet.of(StoneType.class, "squares_stairs",
                        getModBlock("stone_squares_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "squares").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "squares")))
                //TEXTURES: stone_squares
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(squares_stairs);

        squares_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "squares_stairs_connecting",
                        getModBlock("stone_squares_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "squares_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "squares_connecting")))
                //TEXTURES: big_tiles (above)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(squares_stairs_connecting);

        waves_stairs = StoneZoneEntrySet.of(StoneType.class, "waves_stairs",
                        getModBlock("stone_waves_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "waves").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "waves")))
                //TEXTURES: stone_waves
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(waves_stairs);

        waves_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "waves_stairs_connecting",
                        getModBlock("stone_waves_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "waves_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "waves_connecting")))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(waves_stairs_connecting);

        tiles_stairs = StoneZoneEntrySet.of(StoneType.class, "tiles_stairs",
                        getModBlock("stone_tiles_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "tiles").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "tiles")))
                //TEXTURES: stone_tiles
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(tiles_stairs);

        tiles_stairs_connecting = StoneZoneEntrySet.of(StoneType.class, "tiles_stairs_connecting",
                        getModBlock("stone_tiles_stairs_connecting"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(true,
                                getParentBlock(type, "tiles_connecting").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "tiles_connecting")))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(tiles_stairs_connecting);

        slated_stairs = StoneZoneEntrySet.of(StoneType.class, "slated_stairs",
                        getModBlock("stone_slated_stairs"), () -> VanillaStoneTypes.STONE,
                        type -> new RechiseledStairBlock(false,
                                getParentBlock(type, "slated").defaultBlockState(),
                                Utils.copyPropertySafe(type.stone)
                        )
                )
                //REASON: stairs' parent-block
                .addCondition(type -> Objects.nonNull(getParentBlock(type, "slated")))
                //TEXTURES: stone_slated_end, stone_slated_side
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .setTab(tab)
                .build();
        this.addEntry(slated_stairs);

    }


}