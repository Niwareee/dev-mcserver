package fr.niware.serverapi.paper.world;

import org.bukkit.Location;
import org.bukkit.World;

public interface IWorldManager {

    World getWorld();

    Location getSpawnLocation();

    void setSpawnLocation(Location location);
}
