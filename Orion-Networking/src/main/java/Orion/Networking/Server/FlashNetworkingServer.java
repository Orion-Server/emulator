package Orion.Networking.Server;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Api.Util.Dispatchable;
import Orion.Networking.Providers.FlashServerProvider;
import Orion.Networking.Providers.NitroServerProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.bootstrap.ServerBootstrap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;

@Singleton
public class FlashNetworkingServer implements Dispatchable {
    private final String name = "[FlashClient]";

    private final Logger logger = LogManager.getLogger();

    private final FlashServerProvider serverProvider;

    private final IEmulatorEnvironmentSettings environmentSettings;

    private ServerBootstrap flashServer;

    @Inject
    public FlashNetworkingServer(FlashServerProvider serverProvider, IEmulatorEnvironmentSettings environmentSettings) {
        this.serverProvider = serverProvider;
        this.environmentSettings = environmentSettings;

        this.createServer();
    }

    private void createServer() {
        this.flashServer = this.serverProvider.provide();
    }

    public void dispatch() {
        final String hostname = this.environmentSettings.getString("networking.server.host");
        final int port = this.environmentSettings.getInteger("networking.server.flash.port");

        try {
            this.flashServer.bind(new InetSocketAddress(hostname, port)).addListener(future -> {
                if (future.isSuccess()) {
                    this.logger.info(STR."\{this.name} Running on port: \{port}");
                    return;
                }

                this.logger.info(STR."\{this.name} Failed to start on port: \{port}");
            });
        } catch (Exception e) {
            this.logger.info(STR."\{this.name} Failed to start on port: \{port}");
        }
    }
}
