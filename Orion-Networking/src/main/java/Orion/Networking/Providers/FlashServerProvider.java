package Orion.Networking.Providers;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import Orion.Networking.Factory.ServerBootstrapFactory;
import Orion.Networking.Group.EventLoopGroupFactory;
import Orion.Networking.Providers.Channel.FlashSocketChannel;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultMessageSizeEstimator;
import io.netty.channel.WriteBufferWaterMark;

@Singleton
public class FlashServerProvider {
    private final ServerBootstrapFactory serverBootstrapFactory;

    private final EventLoopGroupFactory eventLoopGroupFactory;

    private final IEmulatorEnvironmentSettings environmentSettings;

    private final ISessionManager sessionManager;

    @Inject
    public FlashServerProvider(
            ServerBootstrapFactory serverBootstrapFactory,
            EventLoopGroupFactory eventLoopGroupFactory,
            IEmulatorEnvironmentSettings environmentSettings,
            ISessionManager sessionManager
    ) {
        this.serverBootstrapFactory = serverBootstrapFactory;
        this.eventLoopGroupFactory = eventLoopGroupFactory;
        this.environmentSettings = environmentSettings;
        this.sessionManager = sessionManager;
    }

    public ServerBootstrap provide() {
        final int ioThreads = this.environmentSettings.getInteger("networking.server.flash.io_threads");
        final int acceptThreads = this.environmentSettings.getInteger("networking.server.flash.accept_threads");
        final int channelThreads = this.environmentSettings.getInteger("networking.server.flash.channel_threads");

        final ServerBootstrap nitroServer = this.serverBootstrapFactory.createDefaultServer(
                this.eventLoopGroupFactory.createEventLoopGroup(acceptThreads),
                this.eventLoopGroupFactory.createEventLoopGroup(ioThreads),
                new FlashSocketChannel(
                        this.sessionManager,
                        this.eventLoopGroupFactory.createEventLoopGroup(channelThreads)
                )
        );

        return nitroServer
                .option(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(0x8000,0x10000))
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .option(ChannelOption.MESSAGE_SIZE_ESTIMATOR, DefaultMessageSizeEstimator.DEFAULT);
    }
}
