package org.kots.kotsmixins.mixin;

import me.fallenbreath.conditionalmixin.api.mixin.RestrictiveMixinConfigPlugin;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.LoadingModList;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin extends RestrictiveMixinConfigPlugin {
    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    private static boolean isModLoaded(String modid) {
        if (ModList.get() == null) {
            return LoadingModList.get().getModFileById(modid) != null;
        } else {
            return ModList.get().isLoaded(modid);
        }
    }
}
