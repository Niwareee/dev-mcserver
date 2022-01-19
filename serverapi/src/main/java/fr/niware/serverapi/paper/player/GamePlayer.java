package fr.niware.serverapi.paper.player;

import fr.niware.serverapi.commons.database.player.IAccount;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamePlayer {

    private final IAccount account;
    private Player player;

    public GamePlayer(IAccount account) {
        this.account = account;
    }

    public IAccount getAccount() {
        return this.account;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void clear() {
        this.player.clearActiveItem();
        this.player.setGameMode(GameMode.ADVENTURE);
    }
}
