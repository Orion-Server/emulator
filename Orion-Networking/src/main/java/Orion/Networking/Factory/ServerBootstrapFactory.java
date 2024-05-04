package Orion.Networking.Factory;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Singleton
public class ServerBootstrapFactory {
    private final boolean hasEpollEnabled;

    private final IEmulatorEnvironmentSettings environmentSettings;

    @Inject
    public ServerBootstrapFactory(final IEmulatorEnvironmentSettings environmentSettings) {
        this.hasEpollEnabled = Epoll.isAvailable();

        this.environmentSettings = environmentSettings;
    }

    public ServerBootstrap createDefaultServer(
            final EventLoopGroup IOLoopGroup,
            final EventLoopGroup acceptLoopGroup,
            final ChannelHandler channelInitializer
    ) {
        return new ServerBootstrap()
                .group(IOLoopGroup, acceptLoopGroup)
                .channel(this.getServerChannel())
                .childHandler(channelInitializer)
                .option(ChannelOption.SO_BACKLOG, this.environmentSettings.getIntegerOrDefault("networking.server.backlog", 500))
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(32 * 1024,64 * 1024));
    }

    private Class<? extends ServerSocketChannel> getServerChannel() {
        return this.hasEpollEnabled ? EpollServerSocketChannel.class : NioServerSocketChannel.class;
    }

    public boolean isEpollEnabled() {
        return this.hasEpollEnabled;
    }
}
