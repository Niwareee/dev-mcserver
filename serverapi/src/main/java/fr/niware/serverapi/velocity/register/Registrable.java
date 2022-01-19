package fr.niware.serverapi.velocity.register;

public interface Registrable {
    void register(String commandName, String... aliases);
}
