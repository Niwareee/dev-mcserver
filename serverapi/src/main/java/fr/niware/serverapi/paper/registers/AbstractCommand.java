package fr.niware.serverapi.paper.registers;

import fr.niware.serverapi.paper.AbstractPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractCommand extends Command implements Registrable {

    protected final AbstractPlugin plugin;

    protected AbstractCommand(AbstractPlugin plugin, String name) {
        super(name);
        this.plugin = plugin;
    }

    public void register(JavaPlugin plugin) {
        CommandMap commandMap = plugin.getServer().getCommandMap();
        String pluginName = plugin.getDescription().getName();
        commandMap.register(pluginName, this.getExecutor());
    }

    public String getCommand() {
        return this.getName();
    }

    public Command getExecutor() {
        return this;
    }
}
