package fr.niware.serverapi.paper.config;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.servers.ServerConfig;
import fr.niware.serverapi.commons.file.config.ConfigUtils;

public abstract class AbstractConfigManager implements IConfigManager {

    protected final AbstractPlugin plugin;
    protected final ServerConfig serverConfig;

    private boolean isDamageable;
    private boolean isBuildable;
    private boolean isFoodable;
    private boolean isInteractable;

    protected AbstractConfigManager(AbstractPlugin plugin) {
        this.plugin = plugin;
        this.serverConfig = new ConfigUtils(plugin.getSLF4JLogger(), "config.json", plugin.getDataFolder()).getServerConfig();
        plugin.getServer().setMaxPlayers(this.serverConfig.getMaxPlayers());

        this.isDamageable = false;
        this.isBuildable = false;
        this.isFoodable = false;
        this.isInteractable = false;
    }

    @Override
    public ServerConfig getServerConfig() {
        return this.serverConfig;
    }

    @Override
    public boolean isDamageable() {
        return this.isDamageable;
    }

    @Override
    public void setDamageable(boolean damageable) {
        this.isDamageable = damageable;
    }

    @Override
    public boolean isBuildable() {
        return this.isBuildable;
    }

    @Override
    public void setBuildable(boolean buildable) {
        this.isBuildable = buildable;
    }

    @Override
    public boolean isFoodable() {
        return this.isFoodable;
    }

    @Override
    public void setFoodable(boolean foodable) {
        this.isFoodable = foodable;
    }

    @Override
    public boolean isInteractable() {
        return this.isInteractable;
    }

    @Override
    public void setInteractable(boolean interactable) {
        this.isInteractable = interactable;
    }
}
