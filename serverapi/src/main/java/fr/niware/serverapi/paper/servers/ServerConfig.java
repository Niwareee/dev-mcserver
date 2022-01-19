package fr.niware.serverapi.paper.servers;

public class ServerConfig {

    private final int id;
    private final int maxPlayers;
    private final ServerType serverType;
    private final ServerState serverState;

    public ServerConfig(int id, int maxPlayers, ServerType serverType, ServerState serverState) {
        this.id = id;
        this.maxPlayers = maxPlayers;
        this.serverType = serverType;
        this.serverState = serverState;
    }

    public int getId() {
        return this.id;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public ServerType getServerType() {
        return this.serverType;
    }

    public ServerState getServerState() {
        return this.serverState;
    }
}
