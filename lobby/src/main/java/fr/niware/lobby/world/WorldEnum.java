package fr.niware.lobby.world;

public enum WorldEnum {

    WORLD("world");

    private final String name;

    WorldEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
