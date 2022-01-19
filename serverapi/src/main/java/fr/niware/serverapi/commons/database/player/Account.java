package fr.niware.serverapi.commons.database.player;

import java.util.UUID;

public class Account implements IAccount {

    private final UUID uuid;
    private RankUnit rankUnit;
    private final int coins;

    public Account(UUID uuid) {
        this(uuid, 0, 0);
    }

    public Account(UUID uuid, int power, int coins) {
        this.uuid = uuid;
        this.rankUnit = RankUnit.getRank(power);
        this.coins = coins;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
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
    public int getCoins() {
        return this.coins;
    }
}
