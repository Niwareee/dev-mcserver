package fr.niware.lobby;

import fr.niware.lobby.commands.SpawnCommand;
import fr.niware.lobby.config.LobbyConfig;
import fr.niware.lobby.listeners.ConnectionListener;
import fr.niware.lobby.listeners.DefaultListener;
import fr.niware.lobby.listeners.PlayerListener;
import fr.niware.lobby.scoreboard.LobbyBoard;
import fr.niware.lobby.world.LobbyWorld;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.config.IConfigManager;
import fr.niware.serverapi.paper.game.IGameManager;
import fr.niware.serverapi.paper.player.IPlayerManager;
import fr.niware.serverapi.paper.scoreboard.IBoardManager;
import fr.niware.serverapi.paper.world.IWorldManager;

public final class Lobby extends AbstractPlugin {

    @Override
    public void enable() {
        new SpawnCommand(this).register(this);
        new ConnectionListener(this).register(this);
        new PlayerListener(this).register(this);
        new DefaultListener(this).register(this);

        this.configManager = new LobbyConfig(this);
        this.boardManager = new LobbyBoard(this);
        this.worldManager = new LobbyWorld(this);
    }

    @Override
    public IConfigManager getConfigManager() {
        return this.configManager;
    }

    @Override
    public IPlayerManager getPlayerManager() {
        return null;
    }

    @Override
    public IGameManager getGameManager() {
        return null;
    }

    @Override
    public IBoardManager getBoardManager() {
        return this.boardManager;
    }

    @Override
    public IWorldManager getWorldManager() {
        return this.worldManager;
    }
}
