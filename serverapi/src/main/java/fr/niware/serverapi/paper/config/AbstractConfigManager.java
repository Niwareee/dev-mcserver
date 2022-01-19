package fr.niware.serverapi.paper.config;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.servers.ServerConfig;
import fr.niware.serverapi.commons.file.config.ConfigUtils;

public abstract class AbstractConfigManager implements IConfigManager {

    protected final AbstractPlugin plugin;
    protected ServerConfig serverConfig;

    protected AbstractConfigManager(AbstractPlugin plugin) {
        this.plugin = plugin;
        this.serverConfig = new ConfigUtils(plugin.getSLF4JLogger(), "config.json", plugin.getDataFolder()).getServerConfig();
    }

    @Override
    public ServerConfig getServerConfig() {
        return this.serverConfig;
    }
}
