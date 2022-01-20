package fr.niware.proxy.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.ServerPing;
import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.commons.database.redis.RedisManager;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.velocity.AbstractProxy;
import fr.niware.serverapi.velocity.maintenance.MaintenanceManager;
import fr.niware.serverapi.velocity.players.PlayerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;

public class ProxyListener {

    private final AbstractProxy plugin;
    private final PlayerManager playerManager;
    private final RedisManager redisManager;
    private final MaintenanceManager maintenanceManager;

    public ProxyListener(AbstractProxy plugin) {
        this.plugin = plugin;
        this.playerManager = plugin.getPlayerManager();
        this.redisManager = plugin.getDatabase().getRedisManager();
        this.maintenanceManager = plugin.getMaintenanceManager();
    }

    @Subscribe
    public void onJoin(LoginEvent event) {
        Player player = event.getPlayer();
        if (!this.plugin.getMaintenanceManager().canJoin(player.getUsername())) {
            player.disconnect(Messages.MAINTENANCE.getMessage());
            return;
        }

        IAccount account = this.plugin.getDatabase().getPlayerDao().get(player.getUsername(), player.getUniqueId());
        this.redisManager.sendMessage(account);
        this.playerManager.putPlayer(account);

        player.sendPlayerListHeaderAndFooter(Component.text("§aMyServer - Mini-Jeux"), Component.text("§eBon jeu"));
        player.showTitle(Title.title(Component.text("§eBienvenue"), Component.text("Salut")));
    }

    @Subscribe
    public void onPing(ProxyPingEvent event) {
        String motd = " §7§l» §6MyServer §7- §aMini-Jeux §7- §fdiscord.gg/myserver \n  §cEn développement §7• §e§nmyserver.fr";
        if (this.plugin.getServer().getPlayerCount() >= this.plugin.getServer().getConfiguration().getShowMaxPlayers()) {
            motd = " §7§l» §6MyServer §7- §aMini-Jeux §7- §fdiscord.gg/myserver \n  §cLe serveur est plein §7• §e§nmyserver.fr";
        }

        if (this.maintenanceManager.isEnabled()) {
            motd = " §7§l» §6MyServer §7- §aMini-Jeux §7- §fdiscord.gg/myserver \n  §cMaintenance en cours §7• §e§nmyserver.fr";
        }

        ServerPing serverPing = event.getPing().asBuilder()
                .description(Component.text(motd))
                .version(new ServerPing.Version(event.getPing().getVersion().getProtocol(), this.plugin.getMaintenanceManager().isEnabled() ? "§cMaintenance en cours" : "§41.17+"))
                .build();
        event.setPing(serverPing);
    }
}
