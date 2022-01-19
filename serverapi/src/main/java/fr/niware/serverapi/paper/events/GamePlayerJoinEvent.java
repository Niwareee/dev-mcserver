package fr.niware.serverapi.paper.events;

import fr.niware.serverapi.commons.database.player.RankUnit;
import fr.niware.serverapi.paper.player.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GamePlayerJoinEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final GamePlayer gamePlayer;

    public GamePlayerJoinEvent(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;

        if (gamePlayer.getAccount().getRank() == RankUnit.ADMIN) {
            gamePlayer.getPlayer().setOp(true);
        }
    }

    public GamePlayer getGamePlayer() {
        return this.gamePlayer;
    }

    public Player getPlayer() {
        return this.gamePlayer.getPlayer();
    }

    @Override
    public HandlerList getHandlers() {
        return GamePlayerJoinEvent.HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return GamePlayerJoinEvent.HANDLERS_LIST;
    }
}
