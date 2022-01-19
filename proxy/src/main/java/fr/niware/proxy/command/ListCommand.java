package fr.niware.proxy.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.velocity.AbstractProxy;
import fr.niware.serverapi.velocity.register.AbstractCommand;
import net.kyori.adventure.text.Component;

public class ListCommand extends AbstractCommand {

    public ListCommand(AbstractProxy plugin) {
        super(plugin);
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        if (!(source instanceof Player player)) {
            source.sendMessage(Messages.CONSOLE_COMMAND.getMessage());
            return;
        }

        RegisteredServer registeredServer = this.plugin.getServerManager().getServer(player);
        source.sendMessage(Component.text("§eIl y a actuellement §f" + this.plugin.getServer().getPlayerCount() + " §ejoueurs en ligne. §7(" + registeredServer.getPlayersConnected().size() + " sur votre serveur)"));
    }
}
