package fr.niware.serverapi.commons.file.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.niware.serverapi.commons.database.sql.SQLCredentials;
import fr.niware.serverapi.commons.file.FileUtils;
import fr.niware.serverapi.paper.servers.ServerConfig;
import fr.niware.serverapi.velocity.maintenance.Maintenance;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;

public class ConfigUtils implements IConfig {

    private final Gson gson;
    private final String name;
    private final File file;

    public ConfigUtils(Logger logger, String name, File path) {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        this.name = name;
        this.file = new File(path, name);

        try (InputStream in = ConfigUtils.class.getResourceAsStream("/" + name)) {
            if (in != null) {
                if (path.mkdirs()) {
                    logger.info("{} successfully created !", path);
                }

                if (!this.file.exists()) {
                    Files.copy(in, this.file.toPath());
                    logger.info("{} successfully created !", name);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public SQLCredentials getSQLCredentials() {
        String json = FileUtils.loadFile(this.file);
        Type type = new TypeToken<SQLCredentials>() {
        }.getType();
        return this.gson.fromJson(json, type);
    }

    public ServerConfig getServerConfig() {
        String json = FileUtils.loadFile(this.file);
        Type type = new TypeToken<ServerConfig>() {
        }.getType();
        return this.gson.fromJson(json, type);
    }

    public Maintenance getMaintenance() {
        String json = FileUtils.loadFile(this.file);
        Type type = new TypeToken<Maintenance>() {
        }.getType();
        return this.gson.fromJson(json, type);
    }

    public <T> T getConfig() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        String json = FileUtils.loadFile(this.file);
        Type type = new TypeToken<T>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void save(Maintenance maintenance) {
        String json = this.gson.toJson(maintenance);
        FileUtils.saveFile(this.file, json);
    }
}


