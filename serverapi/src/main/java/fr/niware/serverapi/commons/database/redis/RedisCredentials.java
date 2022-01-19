package fr.niware.serverapi.commons.database.redis;

public class RedisCredentials {

    private final String ip;
    private final String password;
    private final int port;
    private final String client;

    public RedisCredentials(String ip, String password, int port, String client) {
        this.ip = ip;
        this.password = password;
        this.port = port;
        this.client = client;
    }

    public String toURI() {
        return this.ip + ":" + this.port;
    }

    public String getIp() {
        return this.ip;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }

    public String getClient() {
        return this.client;
    }
}
