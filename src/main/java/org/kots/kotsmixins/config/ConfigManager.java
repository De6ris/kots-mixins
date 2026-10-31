package org.kots.kotsmixins.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.Nullable;
import org.kots.kotsmixins.util.Platform;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static final Path COMMON = Platform.getConfigDir().resolve("kotsmixins-common.json");
    public static CommonConfig common = new CommonConfig();
    public static final Path CLIENT = Platform.getConfigDir().resolve("kotsmixins-client.json");
    public static ClientConfig client = new ClientConfig();
    public static final Path SERVER = Platform.getConfigDir().resolve("kotsmixins-server.json");
    public static ServerConfig server = new ServerConfig();

    public static void load() {
        CommonConfig common = load(COMMON, CommonConfig.class);
        if (common != null) ConfigManager.common = common;

        ClientConfig client = load(CLIENT, ClientConfig.class);
        if (client != null) ConfigManager.client = client;

        ServerConfig server = load(SERVER, ServerConfig.class);
        if (server != null) ConfigManager.server = server;
    }

    public static void save() {
        save(COMMON, common);
        save(CLIENT, client);
        save(SERVER, server);
    }

    @Nullable
    private static <T> T load(Path path, Class<T> clazz) {
        if (Files.notExists(COMMON)) {
            save();
            return null;
        }
        try (BufferedReader reader = Files.newBufferedReader(COMMON)) {
            return GSON.fromJson(reader, clazz);
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
        return null;
    }

    private static <T> void save(Path path, T config) {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage(), exception);
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            String json = GSON.toJson(config);
            writer.write(json);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    public static CommonConfig getCommon() {
        return common;
    }

    public static ClientConfig getClient() {
        return client;
    }

    public static ServerConfig getServer() {
        return server;
    }

    static {
        load();
    }
}
