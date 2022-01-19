package fr.niware.serverapi.velocity.maintenance;

public enum MaintenanceState {

    ENABLED("§aactivée"),
    DISABLED("§cdésactivée");

    private final String name;

    MaintenanceState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
