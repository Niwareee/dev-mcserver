package fr.niware.lobby.commands;

import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class SpawnCommand extends AbstractCommand {

    public SpawnCommand(AbstractPlugin plugin) {
        super(plugin, "spawn");
    }

    @Override
    public boolean execute(@Nonnull CommandSender sender, @Nonnull String label, @Nonnull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        player.teleport(this.plugin.getWorldManager().getSpawnLocation());
        return true;
    }
}
