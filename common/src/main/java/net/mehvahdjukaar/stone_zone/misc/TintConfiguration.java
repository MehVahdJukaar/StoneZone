package net.mehvahdjukaar.stone_zone.misc;

import net.minecraft.resources.ResourceLocation;

import java.util.*;

/**
 * ParentId - Example mcwfences:block/parent/grass_topped_wall_post via models/block
 * TextureValue - found under elements > faces > DIRECTION > "texture": "TextureValue" in the model file, ParentId
 *
 * DIRECTION: north, south, so on.
 */
public record TintConfiguration(Set<String> excludeTextureKey, Map<ResourceLocation, Set<String>> parentExcludeTextureKeys) {

    public static final TintConfiguration EMPTY = new TintConfiguration(Set.of(), Map.of());

    public static TintConfiguration createNew() {
        return new TintConfiguration(new HashSet<>(), new HashMap<>());
    }

    /**
     * Exclude one or more specific values in all parent files from tinting
     **/
    public void addTextureValues(String... excludeTextureValues) {
        this.excludeTextureKey.addAll(List.of(excludeTextureValues));
    }

    /**
     * Adds a parent ID and associated texture values to be excluded from tinting. 2 Usages:
     * <ul>
     *     <li>parentId with textureValue: Exclude one or more specific values in one parent file from tinting.</li>
     *     <li>Only parentId: Ensures the entire parent file is excluded from tinting. This can be achieved by passing an empty string as excludeTextureValues</li>
     * </ul>
     */
    public void addParentAndTextureValues(ResourceLocation parentId, String... excludeTextureValues) {
        Set<String> set = Set.of(excludeTextureValues);
        this.parentExcludeTextureKeys.put(parentId, set);
    }

    /**
     * Checks if a texture value is excluded.
     * @return true if the texture value is not in the exclusion list, false otherwise.
     */
    public boolean isExcluded(String textureValue) {
        return !this.excludeTextureKey.contains(textureValue);
    }

    /**
     * Checks if a texture value in a specific parent model file is excluded.
     * @return true if the texture value is not in the exclusion list, false otherwise.
     */
    public boolean isTextureExcludedFor(ResourceLocation parentId, String textureValue) {
        // Exclude textureValue inside parent vile from tinting
        if (this.parentExcludeTextureKeys.containsKey(parentId))
            return !this.parentExcludeTextureKeys.get(parentId).contains(textureValue);

        // Exclude a parent file from tinting
        return !this.parentExcludeTextureKeys.containsKey(parentId);
    }
}
