package fr.niware.lobby;

import fr.niware.lobby.commands.SpawnCommand;
import fr.niware.lobby.config.LobbyConfig;
import fr.niware.lobby.listeners.ConnectionListener;
import fr.niware.lobby.listeners.PlayerListener;
import fr.niware.lobby.scoreboard.LobbyBoard;
import fr.niware.lobby.world.LobbyWorld;
import fr.niware.serverapi.paper.AbstractPlugin;

public final class Lobby extends AbstractPlugin {

    @Override
    public void enable() {
        this.configManager = new LobbyConfig(this);
        this.playerManager = new LobbyManager();
        this.boardManager = new LobbyBoard(this);
        this.worldManager = new LobbyWorld(this);

        System.out.println("test" + this.getPlayerManager());
        System.out.println(this.getPlayerManager());

        new SpawnCommand(this).register(this);
        new ConnectionListener(this).register(this);
        new PlayerListener(this).register(this);

        this.configManager.setDamageable(false);
        this.configManager.setBuildable(false);
        this.configManager.setFoodable(false);
        this.configManager.setInteractable(false);
    }
}
