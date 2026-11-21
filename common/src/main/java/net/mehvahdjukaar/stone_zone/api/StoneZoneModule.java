package net.mehvahdjukaar.stone_zone.api;

import com.google.common.base.Suppliers;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZCreativeModeTab;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.RockType;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntries;
import net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;


public class StoneZoneModule extends SimpleModule {

    public StoneZoneModule(String modId, String shortId) {
        super(modId, shortId, StoneZone.MOD_ID);

        addCommonSZTags();
    }

    /// IMPORTANT: modId must be included in StoneZone's addModToDynamicPack() so the tags will be loaded into world first time
    private void addCommonSZTags() {
        // Architect's Palette
        addBlockTypeTag(StoneType.class, "architects_palette:wardstone", TagKey.create(Registries.BLOCK, Identifier.parse("architects_palette:wizard_blocks")));

        // Tinker's Construct
        addBlockTypeTag(StoneType.class, "tconstruct:seared_stone", TagKey.create(Registries.BLOCK, Identifier.parse("tconstruct:seared_blocks")));
        addBlockTypeTag(StoneType.class, "tconstruct:seared_stone", TagKey.create(Registries.ITEM, Identifier.parse("tconstruct:seared_blocks")));
        addBlockTypeTag(StoneType.class, "tconstruct:scorched_stone", TagKey.create(Registries.BLOCK, Identifier.parse("tconstruct:scorched_blocks")));
        addBlockTypeTag(StoneType.class, "tconstruct:scorched_stone", TagKey.create(Registries.ITEM, Identifier.parse("tconstruct:scorched_blocks")));

        // Caverns And Chasms
        addBlockTypeTag(StoneType.class, "caverns_and_chasms:sugilite", TagKey.create(Registries.BLOCK, Identifier.parse("caverns_and_chasms:static_note_blocks")));
        addBlockTypeTag(StoneType.class, "caverns_and_chasms:cassiterite", TagKey.create(Registries.BLOCK, Identifier.parse("caverns_and_chasms:deflects_projectiles")));
        addBlockTypeTag(StoneType.class, "caverns_and_chasms:cassiterite", TagKey.create(Registries.BLOCK, Identifier.parse("caverns_and_chasms:weaker_deflect_velocity")));
    }

    protected final <T extends Block> Supplier<T> getModBlock(String id, Class<T> blockClass) {
        return memor(id, BuiltInRegistries.BLOCK);
    }

    protected final Supplier<CreativeModeTab> getModTab(String id) {
        return memor(id, BuiltInRegistries.CREATIVE_MODE_TAB);
    }

    protected final Supplier<CreativeModeTab> getTab(Identifier id) {
        return Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.getValue(id));
    }

    protected final Supplier<CreativeModeTab> getTab(ResourceKey<CreativeModeTab> id) {
        return Suppliers.memoize(() -> BuiltInRegistries.CREATIVE_MODE_TAB.getValue(id));
    }

    protected final Supplier<Block> getModBlock(String id) {
        return getModBlock(id, Block.class);
    }

    protected final Supplier<Item> getModItem(String id) {
        return memor(id, BuiltInRegistries.ITEM);
    }

    protected final <B extends BlockEntity> Supplier<BlockEntityType<B>> getModTile(String id, Class<B> tileEntityClass) {
        return memor(id, BuiltInRegistries.BLOCK_ENTITY_TYPE);
    }

    protected final Supplier<BlockEntityType<BlockEntity>> getModTile(String id) {
        return getModTile(id, BlockEntity.class);
    }

    @SuppressWarnings("unchecked")
    private <T> Supplier<T> memor(String id, Registry<?> reg) {
        return Suppliers.memoize(() -> {
            try {
                return (id.contains(":"))
                        ? (T) reg.getOptional(Identifier.parse(id)).orElseThrow()
                        : (T) reg.getOptional(modRes(id)).orElseThrow();
            } catch (Throwable e) {
                throw new IllegalStateException("Could not find \"" + id + "\" in " + reg + ". This likely means that the reigstry entry was renamed in the original mod and EC needs updating. " +
                        "Is the mod, " + this.getModName().toUpperCase() + " up to date, if yes, then downgrade to the previous version & wait for an Every Compat update. Otherwise, update the mod to the latest version.");
            }
        });
    }

    @Override
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        if (SZCreativeModeTab.MOD_TAB != null) {
            return SZCreativeModeTab.MOD_TAB.getKey();
        }
        return null;
    }

    @Override
    public boolean isEntryAlreadyRegistered(String entrySetId, Identifier blockId, BlockType blockType, Registry<?> registry) {

        String blockPath = blockId.getPath();
        //short block name - blockName: <stonetype>_column from the blockId: stonezone:twigs/strata/<stonetype>_column
        String blockName = blockPath.substring(blockPath.lastIndexOf("/") + 1);

        String blockTypeFrom = blockType.getNamespace();

        // Exclude blocks from a mod that are both Supported-Mod & Wood-Mod
        if (blockTypeFrom.equals(this.modId)) return true;

        String slashConvention = blockTypeFrom + "/" + blockName; // quark/blossom_chair
        String underscoreConvention = blockTypeFrom + "_" + blockName; // quark_blossom_chair

        /// Exclude blocks when a Compat-Mod have a support for Supported-Mod with Mods that add new BlockTypes
        for (var c : getOtherCompatMods()) {
            String compatModId = c.modId();  //bopcomp : bop->quark, twigs
            //if the wood is from the mod this adds compat for && it supports this block type
            if (c.woodsFrom().contains(blockTypeFrom) && c.blocksFrom().contains(modId)) {
                if (registry.containsKey(Identifier.fromNamespaceAndPath(compatModId, blockName))) return true;
                if (registry.containsKey(Identifier.fromNamespaceAndPath(compatModId, slashConvention)))
                    return true;
                if (registry.containsKey(Identifier.fromNamespaceAndPath(compatModId, underscoreConvention)))
                    return true;
            }
        }

        // Exclude blocks when a supported-mod have an internal support for mods that add new BlockTypes
        if (this.getAlreadySupportedMods().contains(blockTypeFrom)) return true;

        if (blockType instanceof StoneType stoneType) {
            Boolean hardcoded = HardcodedBlockType.isStoneBlockAlreadyRegistered(entrySetId, blockName, stoneType, modId);
            if (hardcoded != null) return hardcoded;
        }
        else if (blockType instanceof MudType mudType) {
            Boolean hardcoded = HardcodedBlockType.isMudBlockAlreadyRegistered(entrySetId, blockName, mudType, modId);
            if (hardcoded != null) return hardcoded;
        }

        return super.isEntryAlreadyRegistered(entrySetId, blockId, blockType, registry);
    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
    }

    public  <T extends RockType> void addEntries(BlockSetEntries<T> blockSet) {
        this.addEntry(blockSet.block);
        if (blockSet.extra != null) this.addEntry(blockSet.extra);
        if (blockSet.stairs != null) this.addEntry(blockSet.stairs);
        if (blockSet.wall != null) this.addEntry(blockSet.wall);
    }

}
