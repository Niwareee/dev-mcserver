package fr.niware.serverapi.paper.scoreboard;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.player.GamePlayer;
import fr.niware.serverapi.paper.scoreboard.fastboard.FastBoard;
import fr.niware.serverapi.paper.servers.ServerConfig;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractBoardManager implements IBoardManager {

    protected final AbstractPlugin plugin;
    protected final ServerConfig serverConfig;
    private final Map<UUID, FastBoard> boards;

    protected AbstractBoardManager(AbstractPlugin plugin) {
        this.plugin = plugin;
        this.serverConfig = plugin.getConfigManager().getServerConfig();
        this.boards = new HashMap<>();

        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            for (FastBoard board : this.boards.values()) {
                String[] lines = this.getLines(board.getGamePlayer());
                board.updateLines(lines);
            }
        }, 20L, 20L);
    }

    public void handleJoin(GamePlayer gamePlayer) {
        FastBoard board = new FastBoard(gamePlayer);
        board.updateTitle(this.getBoardName());
        this.boards.put(gamePlayer.getPlayer().getUniqueId(), board);
    }

    public void handleLeave(Player player) {
        FastBoard board = this.boards.remove(player.getUniqueId());
        if (board != null) {
            board.delete();
        }
    }
}
