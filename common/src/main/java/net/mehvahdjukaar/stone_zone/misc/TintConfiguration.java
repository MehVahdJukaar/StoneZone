package net.mehvahdjukaar.stone_zone.misc;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record TintConfiguration(Set<String> excludeTextureKeys, Set<String> parentExcludeTextureKeys) {

    public static final TintConfiguration EMPTY = new TintConfiguration(Set.of(), Set.of());

    public static TintConfiguration createNew() {
        return new TintConfiguration(new HashSet<>(), new HashSet<>());
    }

    public TintConfiguration splitForParent() {
        return new TintConfiguration(excludeTextureKeys, Set.of());
    }

    public void addToParent(String... excludeTextureKeys) {
        this.parentExcludeTextureKeys.addAll(List.of(excludeTextureKeys));
    }

    public void add(String... excludeTextureKey) {
        this.excludeTextureKeys.addAll(List.of(excludeTextureKey));
    }

    public boolean isExcluded(String texture) {
        return excludeTextureKeys.contains(texture);
    }
}
