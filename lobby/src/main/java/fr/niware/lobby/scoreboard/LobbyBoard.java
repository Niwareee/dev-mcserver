package fr.niware.lobby.scoreboard;

import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.player.GamePlayer;
import fr.niware.serverapi.paper.scoreboard.AbstractBoardManager;

public class LobbyBoard extends AbstractBoardManager {

    public LobbyBoard(AbstractPlugin plugin) {
        super(plugin);
    }

    @Override
    public String getBoardName() {
        return "§6MyServer";
    }

    @Override
    public String[] getLines(GamePlayer gamePlayer) {
        IAccount playerData = gamePlayer.getAccount();

        return new String[] {
                " ",
                "§7Grade: " + playerData.getRank().getName(),
                "§7Coins: §e" + playerData.getCoins(),
                " ",
                "§7Connectés: §a" + this.plugin.getServer().getOnlinePlayers().size(),
                "§7Lobby: §6#" + this.serverConfig.getId(),
                " ",
                "§eplay.myserver.fr"
        };
    }
}
