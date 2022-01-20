package fr.niware.serverapi.commons.database.player;

import java.util.Arrays;

public enum RankUnit {

    PLAYER("§fJoueur", "§7", "§7", "§7", 0, 3, false),
    HELPER("§bAssistant", "§7[§bA§7] §b", "§7[§bAssistant§7] §b", "§b", 70, 2, true),
    MOD("§2Modo", "§7[§2M§7] §2", "§7[§2Modo§7] §2", "§2", 80, 1, true),
    ADMIN("§4Admin", "§7[§4A§7] §4", "§7[§4Admin§7] §4", "§c", 100, 0, true);

    private final String name;
    private final String tabFormat;
    private final String chatFormat;
    private final String chatColor;
    private final int power;
    private final int order;
    private final boolean notify;

    RankUnit(String name, String tabFormat, String chatFormat, String chatColor, int power, int order, boolean notify) {
        this.name = name;
        this.tabFormat = tabFormat;
        this.chatFormat = chatFormat;
        this.chatColor = chatColor;
        this.order = order;
        this.power = power;
        this.notify = notify;
    }

    public String getName() {
        return this.name;
    }

    public String getTabFormat() {
        return this.tabFormat;
    }

    public String getPrefix() {
        return this.chatFormat;
    }

    public String getChatColor() {
        return this.chatColor;
    }

    public int getPower() {
        return this.power;
    }

    public int getOrder() {
        return this.order;
    }

    public boolean isNotify() {
        return this.notify;
    }

    public static RankUnit getRank(int power) {
        return Arrays.stream(RankUnit.values()).filter(rank -> rank.getPower() == power).findFirst().orElse(RankUnit.PLAYER);
    }
}
