package fr.niware.serverapi.paper.player;

import fr.niware.serverapi.commons.database.player.IAccount;

import java.util.Map;
import java.util.UUID;

public interface IPlayerManager {

    Map<UUID, GamePlayer> getPlayers();

    IAccount getAccount(UUID player);

    GamePlayer getPlayer(UUID player);

    void putPlayer(GamePlayer playerData);

    void removePlayer(UUID uuid);
}
