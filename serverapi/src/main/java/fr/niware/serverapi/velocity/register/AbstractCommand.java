package fr.niware.serverapi.velocity.register;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.command.SimpleCommand;
import fr.niware.serverapi.velocity.AbstractProxy;

public abstract class AbstractCommand implements SimpleCommand, Registrable {

    protected AbstractProxy plugin;

    protected AbstractCommand(AbstractProxy plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register(String commandName, String... aliases) {
        CommandManager commandManager = this.plugin.getServer().getCommandManager();
        CommandMeta meta = commandManager.metaBuilder(commandName).aliases(aliases).build();
        commandManager.register(meta, this);
    }
}
