package fr.niware.proxy;

import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import fr.niware.proxy.command.HubCommand;
import fr.niware.proxy.command.ListCommand;
import fr.niware.proxy.command.MaintenanceCommand;
import fr.niware.proxy.command.PingCommand;
import fr.niware.proxy.listeners.ProxyListener;
import fr.niware.serverapi.velocity.AbstractProxy;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(id = "proxy", name = "Proxy", version = "1.0", authors = "Niware", dependencies = {@Dependency(id = "proxyapi")})
public class Proxy extends AbstractProxy {

    @Inject
    public Proxy(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        super(server, logger, dataDirectory);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        new PingCommand(this).register("ping");
        new HubCommand(this).register("hub", "lobby");
        new ListCommand(this).register("list");
        new MaintenanceCommand(this).register("maintenance");

        EventManager eventManager = this.server.getEventManager();
        eventManager.register(this, new ProxyListener(this));
    }
}
