package fr.niware.lobby.config;

import fr.niware.serverapi.paper.utils.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class Items {

    private Items() {
        throw new IllegalStateException("Utility class");
    }

    public static final ItemStack MENU = new ItemBuilder(Material.COMPASS, 1, "§6Menu")
            .addFlag(ItemFlag.HIDE_ATTRIBUTES)
            .build();

}
