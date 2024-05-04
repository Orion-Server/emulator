package Orion.Networking.Providers;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Networking.Factory.ServerBootstrapFactory;
import Orion.Networking.Group.EventLoopGroupFactory;
import Orion.Networking.Providers.Channel.NitroSocketChannel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultMessageSizeEstimator;
import io.netty.channel.FixedRecvByteBufAllocator;

@Singleton
public class NitroServerProvider {
    private final ISessionManager sessionManager;

    private final EventLoopGroupFactory eventLoopGroupFactory;

    private final ServerBootstrapFactory serverBootstrapFactory;

    private final IEmulatorEnvironmentSettings environmentSettings;

    private final IServerMessageHandler serverMessageHandler;

    @Inject
    public NitroServerProvider(
            ISessionManager sessionManager,
            ServerBootstrapFactory serverBootstrapFactory,
            EventLoopGroupFactory eventLoopGroupFactory,
            IEmulatorEnvironmentSettings environmentSettings,
            IServerMessageHandler serverMessageHandler
    ) {
        this.sessionManager = sessionManager;
        this.serverBootstrapFactory = serverBootstrapFactory;
        this.eventLoopGroupFactory = eventLoopGroupFactory;
        this.environmentSettings = environmentSettings;
        this.serverMessageHandler = serverMessageHandler;
    }

    public ServerBootstrap provide() {
        final int acceptThreads = this.environmentSettings.getInteger("networking.server.nitro.accept_threads");
        final int ioThreads = this.environmentSettings.getInteger("networking.server.nitro.io_threads");

        final ServerBootstrap nitroServer = this.serverBootstrapFactory.createDefaultServer(
                this.eventLoopGroupFactory.createEventLoopGroup(acceptThreads),
                this.eventLoopGroupFactory.createEventLoopGroup(ioThreads),
                new NitroSocketChannel(this.sessionManager, this.serverMessageHandler)
        );

        return nitroServer
                .childOption(ChannelOption.SO_RCVBUF, 4096)
                .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 0)
                .childOption(ChannelOption.MESSAGE_SIZE_ESTIMATOR, DefaultMessageSizeEstimator.DEFAULT)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(4096));
    }
}
