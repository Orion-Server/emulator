package Orion.Networking;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Util.Dispatchable;
import Orion.Networking.Server.FlashNetworkingServer;
import Orion.Networking.Server.NitroNetworkingServer;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4J2LoggerFactory;

@Singleton
public class NetworkManager implements Dispatchable {
    private final NitroNetworkingServer nitroServer;

    private final FlashNetworkingServer flashServer;

    private final ISessionManager sessionManager;

    @Inject
    public NetworkManager(
            final NitroNetworkingServer nitroServer,
            final FlashNetworkingServer flashServer,
            final ISessionManager sessionManager
    ) {
        this.nitroServer = nitroServer;
        this.flashServer = flashServer;
        this.sessionManager = sessionManager;

        this.configureInternal();
    }

    @Override
    public void dispatch() {
        this.nitroServer.dispatch();
        this.flashServer.dispatch();
    }

    private void configureInternal() {
        InternalLoggerFactory.setDefaultFactory(Log4J2LoggerFactory.INSTANCE);

        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.PARANOID);
    }
}
