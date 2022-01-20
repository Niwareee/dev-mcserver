package fr.niware.serverapi.commons.database;

import fr.niware.serverapi.commons.database.dao.PlayerDao;
import fr.niware.serverapi.commons.database.redis.RedisManager;
import fr.niware.serverapi.commons.database.sql.SQLCredentials;
import fr.niware.serverapi.commons.database.sql.SQLDatabase;
import fr.niware.serverapi.commons.file.config.ConfigUtils;
import org.slf4j.Logger;

import java.io.File;

public class Database implements IDatabase {

    private final SQLDatabase sqlDatabase;
    private final RedisManager redisManager;
    private final PlayerDao playerDao;

    public Database(Logger logger, File file, boolean needRedis, boolean needSQL) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        this.redisManager = needRedis ? new RedisManager(logger.getName()) : null;

        this.sqlDatabase = needSQL ? new SQLDatabase(logger, file) : null;
        this.playerDao = needSQL ? new PlayerDao(this.sqlDatabase, logger) : null;
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
