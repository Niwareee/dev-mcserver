package fr.niware.lobby.listeners;

import fr.niware.lobby.config.Items;
import fr.niware.lobby.gui.MainGui;
import fr.niware.serverapi.commons.database.player.RankUnit;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractListener;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener extends AbstractListener {

    public PlayerListener(AbstractPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        ChatRenderer renderer = ChatRenderer.viewerUnaware(this::handleUserChat);
        event.renderer(renderer);
    }

    private Component handleUserChat(Player receiver, Component sourceDisplay, Component message) {
        RankUnit rank = this.plugin.getPlayerManager().getPlayer(receiver.getUniqueId()).getAccount().getRank();
        String playerName = ((TextComponent) sourceDisplay).content();
        String chat = ((TextComponent) message).content();
        return Component.text(rank.getPrefix() + playerName + " §7» " + rank.getChatColor() + chat);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE) {
            event.setCancelled(true);
        }

        if (event.getItem() == null) {
            return;
        }

        if (event.getItem().isSimilar(Items.MENU)) {
            new MainGui(this.plugin).open(player);
        }
    }
}
