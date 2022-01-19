package fr.niware.serverapi.commons.database.dao;

import java.util.UUID;

public interface Dao<T> {

    T get(String playerName, UUID uuid);

    T create(String playerName, UUID uuid);

    void save(String playerName, UUID uuid);

    void updateTables();
}
