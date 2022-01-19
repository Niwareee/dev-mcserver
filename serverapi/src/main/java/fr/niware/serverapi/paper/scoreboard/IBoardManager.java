package fr.niware.serverapi.paper.scoreboard;

import fr.niware.serverapi.paper.player.GamePlayer;
import org.bukkit.entity.Player;

public interface IBoardManager {

    void handleJoin(GamePlayer gamePlayer);

    void handleLeave(Player player);

    String getBoardName();

    String[] getLines(GamePlayer gamePlayer);
}
