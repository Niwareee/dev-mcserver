package fr.niware.lobby.scoreboard;

import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.scoreboard.AbstractBoardManager;
import fr.niware.serverapi.paper.scoreboard.fastboard.FastBoard;

public class LobbyBoard extends AbstractBoardManager {

    public LobbyBoard(AbstractPlugin plugin) {
        super(plugin);
    }

    @Override
    public void create(FastBoard board) {
        IAccount playerData = board.getGamePlayer().getAccount();

        board.updateTitle("§6MyServer");
        board.updateLines(
                "",
                "§7Grade: " + playerData.getRank().getName(),
                "§7Coins: §e" + playerData.getCoins(),
                " ",
                "§7Connectés: §a" + this.plugin.getServer().getOnlinePlayers().size(),
                "§7Lobby: §6#" + this.serverConfig.getId(),
                " ",
                "§eplay.myserver.fr"
        );
    }

    @Override
    public void update(FastBoard board) {
        board.updateLine(4, "§7Connectés: §a" + this.plugin.getServer().getOnlinePlayers().size());
    }
}
