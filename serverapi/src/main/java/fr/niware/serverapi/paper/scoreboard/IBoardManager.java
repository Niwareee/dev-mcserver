package fr.niware.serverapi.paper.scoreboard;

import fr.niware.serverapi.paper.player.GamePlayer;
import fr.niware.serverapi.paper.scoreboard.fastboard.FastBoard;
import org.bukkit.entity.Player;

public interface IBoardManager {

    void handleJoin(GamePlayer gamePlayer);

    void handleLeave(Player player);

    void create(FastBoard board);

    void update(FastBoard board);
}
