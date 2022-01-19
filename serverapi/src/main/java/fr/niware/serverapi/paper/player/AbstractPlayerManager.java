package fr.niware.serverapi.paper.player;

import fr.niware.serverapi.commons.database.player.IAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractPlayerManager implements IPlayerManager {

    private final Map<UUID, GamePlayer> players = new HashMap<>();

    @Override
    public Map<UUID, GamePlayer> getPlayers() {
        return this.players;
    }

    @Override
    public IAccount getAccount(UUID uuid) {
        return this.players.get(uuid).getAccount();
    }

    @Override
    public GamePlayer getPlayer(UUID uuid) {
        return this.players.get(uuid);
    }

    @Override
    public void putPlayer(GamePlayer gamePlayer) {
        this.players.put(gamePlayer.getAccount().getUUID(), gamePlayer);
    }

    @Override
    public void removePlayer(UUID uuid) {
        this.players.remove(uuid);
    }
}
