package fr.niware.serverapi.paper;

import fr.niware.serverapi.commons.database.Database;
import fr.niware.serverapi.commons.database.IDatabase;
import fr.niware.serverapi.paper.commands.InfoCommand;
import fr.niware.serverapi.paper.commands.LagCommand;
import fr.niware.serverapi.paper.commands.VersionCommand;
import fr.niware.serverapi.paper.config.IConfigManager;
import fr.niware.serverapi.paper.listeners.DefaultListener;
import fr.niware.serverapi.paper.listeners.PlayerListener;
import fr.niware.serverapi.paper.listeners.redis.ConnectionRedisListener;
import fr.niware.serverapi.paper.player.IPlayerManager;
import fr.niware.serverapi.paper.scoreboard.IBoardManager;
import fr.niware.serverapi.paper.utils.CommandUnregister;
import fr.niware.serverapi.paper.world.IWorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractPlugin extends JavaPlugin {

    protected IDatabase database;

    protected IConfigManager configManager;
    protected IPlayerManager playerManager;
    protected IBoardManager boardManager;
    protected IWorldManager worldManager;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        try {
            this.database = new Database(this.getSLF4JLogger(), this.getDataFolder(), true, true);
            this.database.getRedisManager().addMessagingListener(new ConnectionRedisListener(this));
        } catch (IllegalStateException exception) {
            this.getPluginLoader().disablePlugin(this);
        }

        this.enable();

        new LagCommand(this).register(this);
        new InfoCommand(this).register(this);
        new PlayerListener(this).register(this);
        new DefaultListener(this).register(this);

        Runtime runtime = Runtime.getRuntime();
        this.getLog4JLogger().info("Running with {} threads and {} Mo.", runtime.availableProcessors(), runtime.maxMemory() / 1024L / 1024L);
        this.getLog4JLogger().info("{} successfully enabled in {} ms", this.getName(), System.currentTimeMillis() - start);

        this.getServer().getScheduler().runTaskLaterAsynchronously(this, () -> {
            CommandUnregister.unregisterCommands("minecraft", "ver", "version", "help", "tell", "me", "trigger", "say");
            CommandUnregister.unregisterCommands("bukkit", "about", "help", "?", "icanhasbukkit");
            new VersionCommand(this).register(this);
        }, 10L);
    }

    @Override
    public void onDisable() {
        this.database.getRedisManager().shutdown();
        this.database.getSQLDatabase().close();
    }

    public IDatabase getDatabase() {
        return this.database;
    }

    public IConfigManager getConfigManager() {
        return this.configManager;
    }

    public IPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public IBoardManager getBoardManager() {
        return this.boardManager;
    }

    public IWorldManager getWorldManager() {
        return this.worldManager;
    }

    public abstract void enable();
}
