package fr.niware.serverapi.paper;

import fr.niware.serverapi.commons.database.Database;
import fr.niware.serverapi.commons.database.IDatabase;
import fr.niware.serverapi.commons.database.redis.RedisManager;
import fr.niware.serverapi.paper.commands.LagCommand;
import fr.niware.serverapi.paper.game.IGameManager;
import fr.niware.serverapi.paper.listeners.redis.ConnectionRedisListener;
import fr.niware.serverapi.paper.player.IPlayerManager;
import fr.niware.serverapi.paper.commands.VersionCommand;
import fr.niware.serverapi.paper.config.IConfigManager;
import fr.niware.serverapi.paper.listeners.PlayerListener;
import fr.niware.serverapi.paper.scoreboard.IBoardManager;
import fr.niware.serverapi.paper.utils.CommandUnregister;
import fr.niware.serverapi.paper.world.IWorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractPlugin extends JavaPlugin {

    protected IDatabase database;

    protected IConfigManager configManager;
    protected IPlayerManager playerManager;
    protected IGameManager gameManager;
    protected IBoardManager boardManager;
    protected IWorldManager worldManager;

    protected RedisManager redisManager;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        try {
            this.database = new Database(this.getSLF4JLogger(), this.getDataFolder());
            this.database.getRedisManager().addMessagingListener(new ConnectionRedisListener(this));
        } catch (IllegalStateException exception) {
            this.getPluginLoader().disablePlugin(this);
        }

        this.enable();

        this.configManager = this.getConfigManager();
        this.playerManager = this.getPlayerManager();
        this.gameManager = this.getGameManager();
        this.boardManager = this.getBoardManager();
        this.worldManager = this.getWorldManager();

        new LagCommand(this).register(this);
        new PlayerListener(this).register(this);

        Runtime runtime = Runtime.getRuntime();
        this.getLog4JLogger().info("Running with {} threads and {} Mo.", runtime.availableProcessors(), runtime.maxMemory() / 1024L / 1024L);
        this.getLog4JLogger().info("{} successfully enabled in {} ms", this.getName(), System.currentTimeMillis() - start);

        this.getServer().getScheduler().runTaskLaterAsynchronously(this, () -> {
            CommandUnregister.unregisterCommands("minecraft", "help", "tell", "me", "trigger", "say");
            CommandUnregister.unregisterCommands("bukkit", "about", "help", "?", "icanhasbukkit");

            new VersionCommand(this).register(this);
        }, 10L);
    }

    @Override
    public void onDisable() {
        this.redisManager.close();
    }

    public void getTest() {

    }

    public IDatabase getDatabase() {
        return this.database;
    }

    public RedisManager getRedisManager() {
        return this.redisManager;
    }

    public abstract void enable();

    public abstract IConfigManager getConfigManager();

    public abstract IPlayerManager getPlayerManager();

    public abstract IGameManager getGameManager();

    public abstract IBoardManager getBoardManager();

    public abstract IWorldManager getWorldManager();
}
