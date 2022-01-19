package fr.niware.serverapi.paper.commands;

import fr.niware.serverapi.commons.utils.Messages;
import fr.niware.serverapi.paper.AbstractPlugin;
import fr.niware.serverapi.paper.registers.AbstractCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class LagCommand extends AbstractCommand {

    public LagCommand(AbstractPlugin plugin) {
        super(plugin, "lag");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.NO_PERMISSION.getMessage());
            return true;
        }

        double[] tps = ((CraftServer) this.plugin.getServer()).getServer().recentTps;
        String[] tpsAvg = new String[tps.length];
        for (int i = 0; i < tps.length; i++) {
            tpsAvg[i] = this.format(tps[i]);
        }

        player.sendMessage(" ");
        player.sendMessage(" §7» §eInformations du serveur:");
        player.sendMessage(" ");
        player.sendMessage("§8• §7Date: §f" + new SimpleDateFormat("HH'h'mm dd/MM/yyyy").format(System.currentTimeMillis()));
        player.sendMessage("§8• §7Serveur: §6" + this.plugin.getConfigManager().getServerConfig().getServerType().getName() + " #" + this.plugin.getConfigManager().getServerConfig().getId());
        player.sendMessage("§8• §7Lag serveur: " + StringUtils.join(tpsAvg, ", "));
        player.sendMessage("§8• §7Lag client: §a" + player.getPing() + " ms");
        player.sendMessage(" ");
        return false;
    }

    private String format(double tps) {
        final double finalTps = Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
        if (tps > 18.0) {
            return ChatColor.GREEN.toString() + finalTps + ((tps > 20.0) ? "*" : "");
        }
        return ((tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString() + finalTps;
    }
}
