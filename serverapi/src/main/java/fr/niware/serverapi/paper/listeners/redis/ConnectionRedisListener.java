package fr.niware.serverapi.paper.listeners.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.commons.database.player.Account;
import fr.niware.serverapi.paper.player.GamePlayer;

import java.lang.reflect.Type;
import java.util.function.Consumer;

public class ConnectionRedisListener implements Consumer<String> {

    private final AbstractPlugin plugin;

    public ConnectionRedisListener(AbstractPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void accept(String msg) {
        long start = System.currentTimeMillis();

        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        Type type = new TypeToken<Account>() {
        }.getType();
        this.plugin.getPlayerManager().putPlayer(new GamePlayer(gson.fromJson(msg, type)));

        this.plugin.getLogger().info("GET Redis in " + (System.currentTimeMillis() - start) + " ms");
    }
}
