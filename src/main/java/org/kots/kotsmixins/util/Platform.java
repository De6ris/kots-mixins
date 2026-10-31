package org.kots.kotsmixins.util;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.Optional;

public class Platform {
    public static Path getConfigDir() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    public static boolean hasMod(String modid) {
        return ModList.get().isLoaded(modid);
    }

    public static Optional<String> getModVersion(String modid) {
        return ModList.get()
                .getModContainerById(modid)
                .map(ModContainer::getModInfo)
                .map(IModInfo::getVersion)
                .map(Object::toString);
    }

    public static ResourceLocation identifier(String namespace, String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
