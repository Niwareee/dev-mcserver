package fr.niware.serverapi.paper.gui;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public final class RInventoryHandler extends AbstractListener {

    private final RInventoryManager inventoryManager;

    public RInventoryHandler(AbstractPlugin plugin) {
        super(plugin);
        this.inventoryManager = new RInventoryManager();
        new RInventoryTask(this.inventoryManager).runTaskTimerAsynchronously(plugin, 0L, 1L);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getInventory().getHolder() instanceof RInventory rInventory) {
            int slot = event.getRawSlot();
            event.setCancelled(true);
            if (rInventory.getSharedMap().containsKey(slot)) {
                rInventory.getSharedMap().get(slot).accept(event);
            }
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof RInventory rInventory) {
            rInventory.getRunnableList().forEach(runnable -> runnable.getRunnable().run());
            this.inventoryManager.put(rInventory);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() != null && event.getInventory().getHolder() instanceof RInventory rInventory) {
            this.inventoryManager.remove(rInventory);
        }
    }
}
