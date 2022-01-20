package fr.niware.lobby.world;

import fr.niware.serverapi.paper.world.AbstractWorldManager;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.WorldBorder;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyWorld extends AbstractWorldManager {

    public LobbyWorld(JavaPlugin plugin) {
        super(plugin, "world");

        this.setSpawnLocation(new Location(this.world, 15.5, 63, -26.5, 0f, 0f));

        Server server = this.plugin.getServer();
        server.setWhitelist(false);
        server.setSpawnRadius(0);

        WorldBorder worldBorder = this.world.getWorldBorder();
        worldBorder.setCenter(this.spawnLocation);
        worldBorder.setSize(2000);

        this.world.getEntities().clear();
        this.world.getLivingEntities().clear();
        this.world.setStorm(false);
        this.world.setThundering(false);
        this.world.setWeatherDuration(0);
        this.world.setThunderDuration(0);
        this.world.setFullTime(12000);
        this.world.setPVP(false);
        this.world.setSpawnLocation(this.spawnLocation);
        this.world.setDifficulty(Difficulty.PEACEFUL);
        this.world.setKeepSpawnInMemory(true);

        this.world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        this.world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, false);
        this.world.setGameRule(GameRule.SPAWN_RADIUS, 0);
        this.world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.world.setGameRule(GameRule.MOB_GRIEFING, false);
        this.world.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
        this.world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.world.setGameRule(GameRule.DO_FIRE_TICK, false);
        this.world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    }

    @Override
    public Location getSpawnLocation() {
        return this.spawnLocation;
    }
}
