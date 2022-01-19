package fr.niware.serverapi.velocity.players;

import fr.niware.serverapi.commons.database.player.IAccount;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, IAccount> players = new HashMap<>();

    public Map<UUID, IAccount> getPlayers() {
        return this.players;
    }

    public IAccount getAccount(UUID uuid) {
        return this.players.get(uuid);
    }

    public void putPlayer(IAccount account) {
        this.players.put(account.getUUID(), account);
    }

    public void removePlayer(UUID uuid) {
        this.players.remove(uuid);
    }
}
