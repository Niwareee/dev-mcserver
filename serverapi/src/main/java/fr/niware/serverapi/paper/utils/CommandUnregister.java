package fr.niware.serverapi.paper.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;

import java.util.Map;

public class CommandUnregister {

    private CommandUnregister() {
        throw new IllegalStateException("Utility class");
    }

    public static void unregisterCommands(String prefix, String... commands) {
        Map<String, Command> knownCommands = Bukkit.getServer().getCommandMap().getKnownCommands();

        for (String command : commands) {
            knownCommands.remove(command);
            knownCommands.remove(prefix + ":" + command);
        }
    }
}
