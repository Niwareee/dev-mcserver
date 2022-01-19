package fr.niware.serverapi.commons.database;

import fr.niware.serverapi.commons.database.dao.PlayerDao;
import fr.niware.serverapi.commons.database.redis.RedisManager;
import fr.niware.serverapi.commons.database.sql.SQLCredentials;
import fr.niware.serverapi.commons.database.sql.SQLDatabase;
import fr.niware.serverapi.commons.file.config.ConfigUtils;
import org.slf4j.Logger;

import java.io.File;

public class Database implements IDatabase {

    protected final SQLDatabase sqlDatabase;
    protected final RedisManager redisManager;
    private final PlayerDao playerDao;

    public Database(Logger logger, File path) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        this.redisManager = new RedisManager(logger.getName());

        SQLCredentials credentials = new ConfigUtils(logger, "database.json", path).getSQLCredentials();
        this.sqlDatabase = new SQLDatabase(logger, credentials);

        this.playerDao = new PlayerDao(this.sqlDatabase, logger);
    }

    @Override
    public SQLDatabase getSQLDatabase() {
        return this.sqlDatabase;
    }

    @Override
    public RedisManager getRedisManager() {
        return this.redisManager;
    }

    @Override
    public PlayerDao getPlayerDao() {
        return this.playerDao;
    }
}
