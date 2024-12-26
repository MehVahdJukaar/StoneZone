package net.mehvahdjukaar.stone_zone.api;

import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.assets.LangBuilder;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.mehvahdjukaar.stone_zone.SZRegistry;
import net.mehvahdjukaar.stone_zone.StoneZone;
import net.mehvahdjukaar.stone_zone.api.set.StoneTypeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class SZModule extends SimpleModule {
    public SZModule(String modId, String shortId) {
        super(modId, shortId, StoneZone.MOD_ID);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    public ResourceKey<CreativeModeTab> getDedicatedTab() {
        return SZRegistry.MOD_TAB.getKey();
    }

    @Override
    public String toString() {
        return "StoneZone " + LangBuilder.getReadableName(modId) + " Module";
    }

    @Override
    public ResourceLocation makeMyRes(String name) {
        return super.makeMyRes(name);
    }

    @Override
    public boolean isEntryAlreadyRegistered(String blockId, BlockType blockType, Registry<?> registry) {

        var isAlreadyRegistered = super.isEntryAlreadyRegistered(blockId, blockType, registry);
        if (isAlreadyRegistered) {
            //noinspection ConstantValue
            return isAlreadyRegistered;
        }

        // blockId: stonezone:twigs/strata/<stonetype>_column | blockName: <stonetype>_column
        String blockName = blockId.substring(blockId.lastIndexOf("/") + 1);

        /// DISABLED FOR NOW, There is no hardcoded Blocks yet
//        if (blockType instanceof StoneType stoneType) {
//            Boolean hardcoded = HardcodedBlockType.isStoneBlockAlreadyRegistered(blockName, stoneType, compatModId, shortenedId());
//            if (hardcoded != null) return hardcoded;
//        }

        return false;
    }

    public BlockBehaviour.Properties copyProperties(Block block) {
        var p = Utils.copyPropertySafe(block);
        var s = StoneTypeRegistry.INSTANCE.getBlockTypeOf(block);
        if (s != null) {
            //hardcoded stuff
        }
        return p;
    }

}
