package fr.niware.serverapi.paper.listeners;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.events.GamePlayerJoinEvent;
import fr.niware.serverapi.paper.events.GamePlayerQuitEvent;
import fr.niware.serverapi.paper.player.GamePlayer;
import fr.niware.serverapi.paper.registers.AbstractListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener extends AbstractListener {

    public PlayerListener(AbstractPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(null);

        Player player = event.getPlayer();
        GamePlayer gamePlayer = this.plugin.getPlayerManager().getPlayer(player.getUniqueId());
        gamePlayer.setPlayer(player);

        this.plugin.getBoardManager().handleJoin(gamePlayer);
        this.plugin.getServer().getPluginManager().callEvent(new GamePlayerJoinEvent(gamePlayer));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(null);

        Player player = event.getPlayer();
        this.plugin.getBoardManager().handleLeave(player);

        GamePlayer gamePlayer = this.plugin.getPlayerManager().removePlayer(player.getUniqueId());
        this.plugin.getServer().getPluginManager().callEvent(new GamePlayerQuitEvent(gamePlayer));
    }
}
