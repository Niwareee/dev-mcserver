package fr.niware.serverapi.commons.database.dao;

import fr.niware.serverapi.commons.database.player.Account;
import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.commons.database.sql.SQLDatabase;
import org.intellij.lang.annotations.Language;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public record PlayerDao(SQLDatabase databaseSQL, Logger logger) implements Dao<IAccount> {

    private static final String TABLE_NAME = "accounts";

    @Override
    public IAccount get(String playerName, UUID uuid) {
        long start = System.currentTimeMillis();
        @Language("MariaDB") String request = "SELECT power,coins,kills,deaths,wins FROM " + PlayerDao.TABLE_NAME + " WHERE player_uuid=?";

        return this.databaseSQL.executeQuery(request, statement -> statement.setString(1, uuid.toString()), resultSet -> {
            if (!resultSet.next()) {
                return this.create(playerName, uuid);
            }

            IAccount account = new Account(uuid, resultSet.getInt("power"), resultSet.getInt("coins"));
            this.logger.info("GET SQL in {} ms", System.currentTimeMillis() - start);
            return account;
        });
    }

    @Override
    public IAccount create(String playerName, UUID uuid) {
        @Language("MariaDB") String request = "INSERT INTO " + PlayerDao.TABLE_NAME + " (player_name, player_uuid, power, coins, kills, deaths, wins, first_connection, last_connection) VALUES (?, ?, '0', '0', '0', '0', '0', ?, ?)";

        this.databaseSQL.executeQuery(request, statement -> {
            statement.setString(1, playerName);
            statement.setString(2, uuid.toString());
            statement.setString(3, new SimpleDateFormat("dd/MM/yyyy 'à' HH:mm:ss").format(new Date()));
            statement.setString(4, "first connection");
            this.logger.info("Successfully created {}'s account", playerName);
        });

        return new Account(uuid);
    }

    @Override
    public void save(String playerName, UUID uuid) {
        this.logger.info("Successfully saved {}'s account", playerName);
    }

    @Override
    public void updateTables() {
        this.databaseSQL.update("CREATE TABLE IF NOT EXISTS " + PlayerDao.TABLE_NAME + " (id int(11) AUTO_INCREMENT NOT NULL UNIQUE, player_name TEXT NOT NULL, player_uuid VARCHAR(255) NOT NULL UNIQUE, power INT NOT NULL, coins INT NOT NULL, kills INT NOT NULL, deaths INT NOT NULL, wins INT NOT NULL, first_connection TEXT NOT NULL, last_connection TEXT NOT NULL)");
    }
}
