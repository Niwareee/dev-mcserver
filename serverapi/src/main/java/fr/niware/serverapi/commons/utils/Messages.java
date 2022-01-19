package fr.niware.serverapi.commons.utils;

import net.kyori.adventure.text.Component;

public enum Messages {

    SERVER_NAME("MyServer"),
    CONSOLE_COMMAND("§cErreur: Commande impossible depuis la console."),
    NO_PERMISSION("§cErreur: Vous n'avez pas la permission."),
    MAINTENANCE("§cLe serveur est en maintenance\n§cRetour très bientôt"),
    HUB_ALREADY_CONNECTED("§cErreur: Vous êtes déjà connecté sur ce hub."),
    ;

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public Component getMessage() {
        return Component.text(this.message);
    }
}
