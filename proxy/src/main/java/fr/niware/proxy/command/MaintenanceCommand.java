package fr.niware.proxy.command;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.velocity.AbstractProxy;
import fr.niware.serverapi.velocity.maintenance.MaintenanceManager;
import fr.niware.serverapi.velocity.maintenance.MaintenanceState;
import fr.niware.serverapi.velocity.register.AbstractCommand;
import net.kyori.adventure.text.Component;

public class MaintenanceCommand extends AbstractCommand {

    private final MaintenanceManager maintenanceManager;

    public MaintenanceCommand(AbstractProxy plugin) {
        super(plugin);
        this.maintenanceManager = plugin.getMaintenanceManager();
    }

    @Override
    public void execute(final Invocation invocation) {
        CommandSource source = invocation.source();
        if (!(source instanceof Player player)) {
            source.sendMessage(Messages.CONSOLE_COMMAND.getMessage());
            return;
        }

        IAccount account = this.plugin.getPlayerManager().getAccount(player.getUniqueId());
        if (account.getRank().getPower() < 50) {
            source.sendMessage(Messages.NO_PERMISSION.getMessage());
            return;
        }

        String[] args = invocation.arguments();
        if (args.length == 0) {
            source.sendMessage(Component.text("§cUtilisation: /maintenance status/on/off/add/remove/list."));
            return;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "status" -> {
                    MaintenanceState state = this.maintenanceManager.getState();
                    source.sendMessage(Component.text("§eLa maintenance est actuellement " + state.getName() + "§e."));
                    return;
                }

                case "on" -> {
                    this.maintenanceManager.setState(MaintenanceState.ENABLED);
                    source.sendMessage(Component.text("§eLa maintenance est maintenant " + MaintenanceState.ENABLED.getName() + "§e."));
                    return;
                }

                case "off" -> {
                    this.maintenanceManager.setState(MaintenanceState.DISABLED);
                    source.sendMessage(Component.text("§eLa maintenance est maintenant " + MaintenanceState.DISABLED.getName() + "§e."));
                    return;
                }

                case "list" -> {
                    source.sendMessage(Component.text("§eListe des joueurs:"));
                    this.maintenanceManager.getPlayers().forEach(playerName -> source.sendMessage(Component.text(" §7» §b" + playerName)));
                }

                default -> {
                    return;
                }
            }
        }

        if (args.length == 2) {
            if ("add".equals(args[0])) {
                this.maintenanceManager.addPlayer(args[1]);
                source.sendMessage(Component.text(" §7» §eLe joueur §f" + args[1] + " §ea été ajouté à la maintenance."));
                return;
            }

            if ("remove".equals(args[0])) {
                this.maintenanceManager.removePlayer(args[1]);
                source.sendMessage(Component.text(" §7» §eLe joueur §f" + args[1] + " §ea été enlevé de la maintenance."));
            }
        }
    }
}
