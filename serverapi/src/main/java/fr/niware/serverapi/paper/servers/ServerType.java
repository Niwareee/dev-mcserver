package fr.niware.serverapi.paper.servers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ServerType {

    LOBBY("Lobby", null),
    GAMES("Jeux", new ItemStack(Material.FIREWORK_ROCKET)),
    ;

    private final String name;
    private final ItemStack item;

    ServerType(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack toItemStack() {
        return this.item;
    }

    public static ServerType getByName(String name) {
        for (ServerType current : ServerType.values()) {
            if (name.equals(current.getName())) {
                return current;
            }
        }
        return ServerType.LOBBY;
    }
}
