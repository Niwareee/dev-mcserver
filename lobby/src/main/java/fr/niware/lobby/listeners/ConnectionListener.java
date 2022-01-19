package fr.niware.lobby.listeners;

import fr.niware.lobby.config.Items;
import fr.niware.serverapi.commons.database.player.RankUnit;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.events.GamePlayerJoinEvent;
import fr.niware.serverapi.paper.events.GamePlayerQuitEvent;
import fr.niware.serverapi.paper.player.GamePlayer;
import fr.niware.serverapi.paper.registers.AbstractListener;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class ConnectionListener extends AbstractListener {

    public ConnectionListener(AbstractPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(GamePlayerJoinEvent event) {
        GamePlayer gamePlayer = event.getGamePlayer();
        gamePlayer.clear();

        Player player = event.getPlayer();
        player.getInventory().setItem(4, Items.MENU);
        player.teleportAsync(this.plugin.getWorldManager().getSpawnLocation());

        RankUnit rank = gamePlayer.getAccount().getRank();
        if (rank.isNotify()) {
            this.plugin.getServer().broadcast(Component.text(rank.getPrefix() + player.getName() + " §7a rejoint le hub !"));
        }
    }

    @EventHandler
    public void onQuit(GamePlayerQuitEvent event) {
        // Player Quit
    }
}
