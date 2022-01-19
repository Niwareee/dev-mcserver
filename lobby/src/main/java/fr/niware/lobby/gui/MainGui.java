package fr.niware.lobby.gui;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.gui.RInventory;
import fr.niware.serverapi.paper.utils.builder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

public class MainGui extends RInventory {

    public MainGui(AbstractPlugin plugin) {
        super(plugin);

        this.setItem(new int[]{0, 1, 2, 6, 7, 8, 9, 17, 27, 35, 36, 37, 38, 42, 43, 44}, new ItemBuilder(Material.GLASS_PANE, 1).setDurability(11).setName(" ").build());
        this.setItem(new int[]{3, 5, 11, 15, 21, 23, 29, 30, 31, 32, 33}, new ItemBuilder(Material.GLASS_PANE, 1).setDurability(3).setName(" ").build());
        this.setItem(new int[]{4, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 24, 34, 39, 41}, new ItemBuilder(Material.GLASS_PANE, 1).setDurability(0).setName(" ").build());

        this.setItem(19, new ItemBuilder(Material.GOLDEN_APPLE).setName("§6UHC Host").addLoreLine("§7Serveur de parties personnalisées").addLoreLine("§7publiques et organisables à partir").addLoreLine("§7du grade Host.").addLoreLine("").addLoreLine("§7Joueurs: §a").build(), clickEvent -> {
            System.out.println("clickopen");
        });

        this.setItem(13, new ItemBuilder(Material.DIAMOND_SWORD).setName("§3Practice").addLoreLine("§7Passionné de PvP ? Venez défier").addLoreLine("§7d'autres joueurs, tout en").addLoreLine("§7améliorant votre talent").addLoreLine("§7et votre classement.").addLoreLine("").addLoreLine("§7Joueurs: §a").addFlag(ItemFlag.HIDE_ATTRIBUTES).build(), quickEvent -> {
            this.player.closeInventory();
            System.out.println("clickopen");
        });
    }

    @Override
    protected String getName() {
        return "Menu principal";
    }

    @Override
    protected int getSize() {
        return 5;
    }

    @Override
    public void open(Player player) {
        this.setItem(40, new ItemBuilder(Material.PLAYER_HEAD).setName(player.getName()).build(), clickEvent -> {

        });

        super.open(player);

    }
}
