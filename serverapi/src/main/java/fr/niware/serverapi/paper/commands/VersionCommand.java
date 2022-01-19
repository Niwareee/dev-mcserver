package fr.niware.serverapi.paper.commands;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

import javax.annotation.Nonnull;

public class VersionCommand extends AbstractCommand {

    public VersionCommand(AbstractPlugin plugin) {
        super(plugin, "version");
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String label, String[] args) {
        PluginDescriptionFile description = this.plugin.getDescription();
        sender.sendMessage("§a" + description.getName() + " v" + description.getVersion() + " sur la version " + description.getAPIVersion());
        return true;
    }
}
