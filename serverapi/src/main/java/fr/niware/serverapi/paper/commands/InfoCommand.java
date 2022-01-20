package fr.niware.serverapi.paper.commands;

import fr.niware.serverapi.commons.database.dao.PlayerDao;
import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class InfoCommand extends AbstractCommand {

    private final PlayerDao playerDao;

    public InfoCommand(AbstractPlugin plugin) {
        super(plugin, "infos");
        this.playerDao = plugin.getDatabase().getPlayerDao();
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Messages.CONSOLE_COMMAND.getMessage());
            return true;
        }

        IAccount account = this.playerDao.getByName(sender.getName());
        if (account.getRank().getPower() < 50) {
            sender.sendMessage(Messages.NO_PERMISSION.getMessage());
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(this.getMessage(account, sender.getName()));
            return true;
        }

        IAccount targetAccount = this.playerDao.getByName(args[0]);
        if (targetAccount == null) {
            sender.sendMessage(Messages.PLAYER_NOT_CONNECTED.getMessage(args[0]));
            return true;
        }

        sender.sendMessage(this.getMessage(targetAccount, args[0]));
        return true;
    }

    private String getMessage(IAccount account, String playerName) {
        return
                " \n" +
                " §7» §eInfos du joueur: §f" + playerName + "\n" +
                "§8• §7Rang: §f" + account.getRank().getName() + "\n" +
                "§8• §7Coins: §e" + account.getCoins() + "\n" +
                "§8• §7Première connexion: §f" + account.getFirstConnection() + "\n" +
                "§8• §7Dernière connexion: §f" + account.getLastConnection() + "\n" +
                " \n";
    }
}
