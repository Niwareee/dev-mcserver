package fr.niware.serverapi.commons.database.sql;

public class SQLCredentials {

    private final String host;
    private final String name;
    private final String username;
    private final String password;
    private final int port;

    public SQLCredentials(String host, String name, String username, String password, int port) {
        this.host = host;
        this.name = name;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public String getHost() {
        return this.host;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPort() {
        return this.port;
    }
}

