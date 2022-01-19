package fr.niware.serverapi.paper.servers;

public enum ServerState {

    BOOTING("§cDémarrage...", 1),
    WHITELISTED("§dWhitelist", 13),
    ONLINE("§aOuvert", 10),
    PLAYING("§bEn jeu", 12),
    CLOSING("§6Arrêt...", 14),
    ;

    private final String name;
    private final int color;

    ServerState(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public int getColor() {
        return this.color;
    }
}
