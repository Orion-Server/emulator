package Orion.Module;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Networking.Codec.Message.Flash.FlashMessageEncoder;
import Orion.Networking.Factory.ServerBootstrapFactory;
import Orion.Networking.Group.EventLoopGroupFactory;
import Orion.Networking.NetworkManager;
import Orion.Networking.Providers.FlashServerProvider;
import Orion.Networking.Providers.NitroServerProvider;
import Orion.Networking.Server.FlashNetworkingServer;
import Orion.Networking.Server.NitroNetworkingServer;
import Orion.Networking.Session.SessionManager;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

@Singleton
public class NetworkingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServerBootstrapFactory.class).asEagerSingleton();
        bind(EventLoopGroupFactory.class).asEagerSingleton();
        bind(NetworkManager.class).asEagerSingleton();

        bind(NitroServerProvider.class).asEagerSingleton();
        bind(FlashServerProvider.class).asEagerSingleton();

        bind(NitroNetworkingServer.class).asEagerSingleton();
        bind(FlashNetworkingServer.class).asEagerSingleton();

        bind(ISessionManager.class).to(SessionManager.class);

        bind(FlashMessageEncoder.class).asEagerSingleton();
    }
}
