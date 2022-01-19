package fr.niware.serverapi.velocity;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import fr.niware.serverapi.commons.database.Database;
import fr.niware.serverapi.velocity.maintenance.MaintenanceManager;
import fr.niware.serverapi.velocity.players.PlayerManager;
import fr.niware.serverapi.velocity.server.ServerManager;
import org.slf4j.Logger;

import java.nio.file.Path;

public abstract class AbstractProxy {

    protected final ProxyServer server;
    protected final Logger logger;

    protected Database database;
    protected ServerManager serverManager;
    protected PlayerManager playerManager;
    protected MaintenanceManager maintenanceManager;

    protected AbstractProxy(ProxyServer server, Logger logger, Path dataDirectory) {
        long start = System.currentTimeMillis();

        this.server = server;
        this.logger = logger;

        try {
            this.database = new Database(logger, dataDirectory.toFile());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        this.database.getPlayerDao().updateTables();
        this.serverManager = new ServerManager(this);
        this.playerManager = new PlayerManager();
        this.maintenanceManager = new MaintenanceManager(logger, dataDirectory.toFile());

        Runtime runtime = Runtime.getRuntime();
        logger.info("Running with {} threads and {} Mo.", runtime.availableProcessors(), runtime.maxMemory() / 1024L / 1024L);
        logger.info("Plugin successfully enabled in {} ms", System.currentTimeMillis() - start);
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        this.database.getRedisManager().close();
        this.maintenanceManager.save();
    }

    public ProxyServer getServer() {
        return this.server;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Database getDatabase() {
        return this.database;
    }

    public ServerManager getServerManager() {
        return this.serverManager;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public MaintenanceManager getMaintenanceManager() {
        return this.maintenanceManager;
    }
}

