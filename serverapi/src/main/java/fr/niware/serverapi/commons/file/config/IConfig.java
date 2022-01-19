package fr.niware.serverapi.commons.file.config;

import fr.niware.serverapi.velocity.maintenance.Maintenance;

import java.io.File;

public interface IConfig {

    String getName();

    File getFile();

    void save(Maintenance maintenance);
}
