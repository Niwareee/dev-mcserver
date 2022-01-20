package fr.niware.serverapi.commons.database.player;

import java.util.UUID;

public interface IAccount {

    UUID getUUID();

    RankUnit getRank();

    void setRank(RankUnit rank);

    int getCoins();

    String getFirstConnection();

    String getLastConnection();
}
