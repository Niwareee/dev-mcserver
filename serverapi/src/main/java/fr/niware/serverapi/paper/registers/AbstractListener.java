package fr.niware.serverapi.paper.registers;

import fr.niware.serverapi.paper.AbstractPlugin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractListener implements Registrable, Listener {

    protected final AbstractPlugin plugin;

    protected AbstractListener(AbstractPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this.getListener(), plugin);
    }

    public Listener getListener() {
        return this;
    }
}