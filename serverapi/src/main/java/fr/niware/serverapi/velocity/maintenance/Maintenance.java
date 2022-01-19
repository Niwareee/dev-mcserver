package fr.niware.serverapi.velocity.maintenance;

import java.util.List;

public class Maintenance {

    private MaintenanceState state;
    private final List<String> players;

    public Maintenance(MaintenanceState state, List<String> players) {
        this.state = state;
        this.players = players;
    }

    public MaintenanceState getState() {
        return this.state;
    }

    public boolean isEnabled() {
        return this.state == MaintenanceState.ENABLED;
    }

    public void setState(MaintenanceState state) {
        this.state = state;
    }

    public List<String> getPlayers() {
        return this.players;
    }

    public boolean contains(String player) {
        return this.players.contains(player);
    }

    public void addPlayer(String player) {
        if (!this.contains(player)) {
            this.players.add(player);
        }
    }

    public void removePlayer(String player) {
        this.players.remove(player);
    }
}

