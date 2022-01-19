package fr.niware.serverapi.velocity.server;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import fr.niware.serverapi.velocity.AbstractProxy;

import java.util.Optional;

public record ServerManager(AbstractProxy plugin) {

    public RegisteredServer getHubServer() {
        Optional<RegisteredServer> optionalServer = this.plugin.getServer().getServer("lobby");
        return optionalServer.orElse(null);
    }

    public RegisteredServer getServer(Player player) {
        Optional<ServerConnection> optionalServer = player.getCurrentServer();
        assert optionalServer.orElse(null) != null;
        return optionalServer.orElse(null).getServer();
    }
}
