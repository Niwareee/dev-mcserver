package fr.niware.serverapi.commons.database;

import fr.niware.serverapi.commons.database.dao.PlayerDao;
import fr.niware.serverapi.commons.database.redis.RedisManager;
import fr.niware.serverapi.commons.database.sql.SQLDatabase;

public interface IDatabase {

    SQLDatabase getSQLDatabase();

    RedisManager getRedisManager();

    PlayerDao getPlayerDao();
}
