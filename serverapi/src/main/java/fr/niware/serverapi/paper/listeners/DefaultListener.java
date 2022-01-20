package fr.niware.serverapi.paper.listeners;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.config.IConfigManager;
import fr.niware.serverapi.paper.registers.AbstractListener;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DefaultListener extends AbstractListener {

    private final IConfigManager configManager;

    public DefaultListener(AbstractPlugin plugin) {
        super(plugin);
        this.configManager = plugin.getConfigManager();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setCancelled(!this.configManager.isDamageable());
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        event.setCancelled(!this.configManager.isFoodable());
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent event) {
        event.setCancelled(!this.configManager.isInteractable());
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(!this.configManager.isInteractable() && event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(!this.configManager.isInteractable() && event.getWhoClicked().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        event.setCancelled(!this.configManager.isBuildable() && event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        event.setCancelled(!this.configManager.isBuildable() && event.getPlayer().getGameMode() != GameMode.CREATIVE);
    }
}

