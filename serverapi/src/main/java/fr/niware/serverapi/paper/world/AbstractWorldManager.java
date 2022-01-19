package fr.niware.serverapi.paper.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractWorldManager implements IWorldManager {

    protected final JavaPlugin plugin;
    protected final World world;
    protected Location spawnLocation;

    protected AbstractWorldManager(JavaPlugin plugin, String worldName) {
        this.plugin = plugin;
        this.world = plugin.getServer().getWorld(worldName);
        this.spawnLocation = new Location(this.world, 0, 200, 0);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public Location getSpawnLocation() {
        return this.spawnLocation;
    }

    @Override
    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
