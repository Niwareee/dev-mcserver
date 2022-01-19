package fr.niware.serverapi.velocity.maintenance;

import fr.niware.serverapi.commons.file.config.ConfigUtils;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

public class MaintenanceManager {

    private final ConfigUtils config;
    private final Maintenance maintenance;

    public MaintenanceManager(Logger logger, File path) {
        this.config = new ConfigUtils(logger, "maintenance.json", path);
        this.maintenance = this.config.getMaintenance();
    }

    public MaintenanceState getState() {
        return this.maintenance.getState();
    }

    public List<String> getPlayers() {
        return this.maintenance.getPlayers();
    }

    public void save() {
        this.config.save(this.maintenance);
    }

    public boolean isEnabled() {
        return this.maintenance.isEnabled();
    }

    public void setState(MaintenanceState state) {
        this.maintenance.setState(state);
    }

    public void addPlayer(String player) {
        this.maintenance.addPlayer(player);
    }

    public void removePlayer(String player) {
        this.maintenance.removePlayer(player);
    }

    public boolean canJoin(String playerName) {
        if (this.isEnabled()) {
            return this.maintenance.contains(playerName);
        }
        return true;
    }
}
