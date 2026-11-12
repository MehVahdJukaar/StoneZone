package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.RockType;
import net.mehvahdjukaar.stone_zone.api.set.mud.MudType;
import net.mehvahdjukaar.stone_zone.api.set.stone.StoneType;
import net.mehvahdjukaar.stone_zone.common_classes.BlockSetEntries;
import net.mehvahdjukaar.stone_zone.misc.HardcodedBlockType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;


public class StoneZoneModule extends EveryCompatModule {

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

    @Override
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        if (SZRegistry.MOD_TAB != null) {
            return SZRegistry.MOD_TAB.getKey();
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

    @Override
    public void addDynamicClientResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicClientResources(executor);
        executor.accept((resourceManager, resourceSink) ->
            getEntries().forEach(entrySetParent -> {
                if (entrySetParent instanceof StoneZoneEntrySet<?,?> entrySet) {
                    try {
                        entrySet.generateModels(this, resourceManager, resourceSink);
                    } catch (Exception ex) {
                        StoneZone.LOGGER.error("Failed to generate client resources for EntrySet: {} from module {}:", entrySet, this, ex);
                        if (PlatHelper.isDev()) throw ex;
                    }
                }
            })
        );
    }

    public  <T extends RockType> void addEntries(BlockSetEntries<T> blockSet) {
        this.addEntry(blockSet.block);
        if (blockSet.extra != null) this.addEntry(blockSet.extra);
        if (blockSet.stairs != null) this.addEntry(blockSet.stairs);
        if (blockSet.wall != null) this.addEntry(blockSet.wall);
    }

}
