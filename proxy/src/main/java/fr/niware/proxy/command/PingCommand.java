package fr.niware.proxy.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.velocity.AbstractProxy;
import fr.niware.serverapi.velocity.register.AbstractCommand;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public final class PingCommand extends AbstractCommand {

    public PingCommand(AbstractProxy plugin) {
        super(plugin);
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        String[] args = invocation.arguments();

        if (args.length == 0) {
            Player player = (Player) source;
            player.sendMessage(Component.text(" §7» §aVotre ping est de §e" + player.getPing() + " ms§a."));
            return;
        }

        if (args.length != 1) {
            source.sendMessage(Component.text("§cUtilisation: /ping <Pseudo>."));
            return;
        }

        Optional<Player> optionalTarget = this.plugin.getServer().getPlayer(args[0]);
        if (optionalTarget.isEmpty()) {
            source.sendMessage(Messages.PLAYER_NOT_CONNECTED.getMessage(args[0]));
            return;
        }

        Player target = optionalTarget.get();
        source.sendMessage(Component.text(" §7» §aLe ping de §e" + target.getUsername() + " §aest de §e" + target.getPing() + " ms§a."));
    }
}
