package fr.niware.serverapi.paper.config;

import fr.niware.serverapi.paper.servers.ServerConfig;

public interface IConfigManager {

    ServerConfig getServerConfig();

    boolean isDamageable();

    void setDamageable(boolean damageable);

    boolean isBuildable();

    void setBuildable(boolean buildable);

    boolean isFoodable();

    void setFoodable(boolean foodable);

    boolean isInteractable();

    void setInteractable(boolean foodable);
}
