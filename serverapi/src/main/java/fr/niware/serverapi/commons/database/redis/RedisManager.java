package fr.niware.serverapi.commons.database.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.niware.serverapi.commons.database.player.IAccount;
import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class RedisManager {

    private final RedissonClient redisson;
    private final List<Consumer<String>> messagingListeners = new ArrayList<>();

    public RedisManager(String name) throws IllegalStateException {
        Config config = new Config();
        config.setCodec(new SerializationCodec());

        RedisCredentials credentials = new RedisCredentials("redis://localhost", "8A3JXiSM8Ja722KP5wtRF4mvRxx5cEDG2be36C53fxxrWTn6Uv8am23vQY8U959b", 6379, name);
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setConnectionMinimumIdleSize(5);
        serverConfig.setConnectionPoolSize(5);
        serverConfig.setAddress(credentials.toURI());
        serverConfig.setPassword(credentials.getPassword());
        serverConfig.setDatabase(1);
        serverConfig.setClientName(credentials.getClient());
        this.redisson = Redisson.create(config);

        this.redisson.getTopic("messaging").addListener(String.class, (channel, msg) -> this.messagingListeners.forEach(stringConsumer -> stringConsumer.accept(msg)));
    }

    public void addMessagingListener(Consumer<String> consumer) {
        this.messagingListeners.add(consumer);
    }

    public void sendMessage(IAccount playerData) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .disableHtmlEscaping()
                .create();
        this.redisson.getTopic("messaging").publish(gson.toJson(playerData));
    }

    public IAccount getPlayerData(UUID uuid) {
        RLiveObjectService liveObjectService = this.redisson.getLiveObjectService();
        return liveObjectService.get(IAccount.class, uuid);
    }

    public void shutdown() {
        this.redisson.shutdown();
    }
}