package fr.niware.serverapi.commons.database.dao;

import fr.niware.serverapi.commons.database.player.Account;
import fr.niware.serverapi.commons.database.player.IAccount;
import fr.niware.serverapi.commons.database.sql.SQLDatabase;
import org.intellij.lang.annotations.Language;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class PlayerDao implements Dao<IAccount> {

    private static final String TABLE_NAME = "accounts";

    private final SQLDatabase sqlDatabase;
    private final Logger logger;
    private final SimpleDateFormat dateFormat;

    public PlayerDao(SQLDatabase sqlDatabase, Logger logger) {
        this.sqlDatabase = sqlDatabase;
        this.logger = logger;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm");
    }

    @Override
    public IAccount get(String playerName, UUID uuid) {
        long start = System.currentTimeMillis();
        @Language("MariaDB") String request = "SELECT power, coins, kills, deaths, wins, first_connection, last_connection FROM " + PlayerDao.TABLE_NAME + " WHERE player_uuid=?";

        return this.sqlDatabase.executeQuery(request, statement -> statement.setString(1, uuid.toString()), resultSet -> {
            if (!resultSet.next()) {
                return this.create(playerName, uuid);
            }

            IAccount account = new Account(uuid, resultSet.getInt("power"), resultSet.getInt("coins"), this.dateFormat.format(resultSet.getTimestamp("first_connection")), this.dateFormat.format(resultSet.getTimestamp("first_connection")));
            this.logger.info("GET SQL in {} ms", System.currentTimeMillis() - start);
            return account;
        });
    }

    public IAccount getByName(String playerName) {
        long start = System.currentTimeMillis();
        @Language("MariaDB") String request = "SELECT player_uuid, power, coins, kills, deaths, wins, first_connection, last_connection FROM " + PlayerDao.TABLE_NAME + " WHERE player_name=?";

        return this.sqlDatabase.executeQuery(request, statement -> statement.setString(1, playerName), resultSet -> {
            if (!resultSet.next()) {
                return null;
            }

            IAccount account = new Account(UUID.fromString(resultSet.getString("player_uuid")), resultSet.getInt("power"), resultSet.getInt("coins"), this.dateFormat.format(resultSet.getTimestamp("first_connection")), this.dateFormat.format(resultSet.getTimestamp("first_connection")));
            this.logger.info("GET SQL in {} ms", System.currentTimeMillis() - start);
            return account;
        });
    }

    @Override
    public IAccount create(String playerName, UUID uuid) {
        @Language("MariaDB") String request = "INSERT INTO " + PlayerDao.TABLE_NAME + " (player_name, player_uuid) VALUES (?, ?)";

        this.sqlDatabase.executeQuery(request, statement -> {
            statement.setString(1, playerName);
            statement.setString(2, uuid.toString());
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
        this.sqlDatabase.update("CREATE TABLE IF NOT EXISTS " + PlayerDao.TABLE_NAME + " (id int(11) AUTO_INCREMENT NOT NULL UNIQUE, player_name TEXT NOT NULL, player_uuid VARCHAR(255) NOT NULL UNIQUE, power INT NOT NULL DEFAULT '0', coins INT NOT NULL DEFAULT '0', kills INT NOT NULL DEFAULT '0', deaths INT NOT NULL DEFAULT '0', wins INT NOT NULL DEFAULT '0', first_connection TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, last_connection TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");
    }
}
