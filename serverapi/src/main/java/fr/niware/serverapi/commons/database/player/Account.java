package fr.niware.serverapi.commons.database.player;

import java.util.UUID;

public class Account implements IAccount {

    private RankUnit rankUnit;

    private final UUID uuid;
    private final int coins;
    private final String firstConnection;
    private final String lastConnection;

    public Account(UUID uuid) {
        this(uuid, 0, 0, "", "");
    }

    public Account(UUID uuid, int power, int coins, String firstConnection, String lastConnection) {
        this.rankUnit = RankUnit.getRank(power);
        this.uuid = uuid;
        this.coins = coins;
        this.firstConnection = firstConnection;
        this.lastConnection = lastConnection;
    }

    @Override
    public RankUnit getRank() {
        return this.rankUnit;
    }

    @Override
    public void setRank(RankUnit rankUnit) {
        this.rankUnit = rankUnit;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public String getFirstConnection() {
        return this.firstConnection;
    }

    @Override
    public String getLastConnection() {
        return this.lastConnection;
    }
}
