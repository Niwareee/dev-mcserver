package fr.niware.proxy.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.velocity.AbstractProxy;
import fr.niware.serverapi.velocity.register.AbstractCommand;

import java.util.Optional;

public final class HubCommand extends AbstractCommand {

    public HubCommand(AbstractProxy plugin) {
        super(plugin);
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        if (!(source instanceof Player player)) {
            source.sendMessage(Messages.CONSOLE_COMMAND.getMessage());
            return;
        }

        Optional<ServerConnection> optionalServer = player.getCurrentServer();
        if (optionalServer.isEmpty()) {
            return;
        }

        if (optionalServer.get().getServerInfo().getName().equals(this.plugin.getServerManager().getHubServer().getServerInfo().getName())) {
            player.sendMessage(Messages.HUB_ALREADY_CONNECTED.getMessage());
            return;
        }

        player.createConnectionRequest(this.plugin.getServerManager().getHubServer());
    }
}
