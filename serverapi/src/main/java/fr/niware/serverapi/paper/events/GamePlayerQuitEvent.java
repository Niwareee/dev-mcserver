package fr.niware.serverapi.paper.events;

import fr.niware.serverapi.paper.player.GamePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GamePlayerQuitEvent extends Event {

    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final GamePlayer gamePlayer;

    public GamePlayerQuitEvent(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return this.gamePlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return GamePlayerQuitEvent.HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return GamePlayerQuitEvent.HANDLERS_LIST;
    }
}
