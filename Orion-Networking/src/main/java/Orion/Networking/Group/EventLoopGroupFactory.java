package Orion.Networking.Group;

import Orion.Networking.Factory.ServerBootstrapFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

@Singleton
public class EventLoopGroupFactory {
    private final ServerBootstrapFactory serverBootstrapFactory;

    @Inject
    public EventLoopGroupFactory(final ServerBootstrapFactory serverBootstrapFactory) {
        this.serverBootstrapFactory = serverBootstrapFactory;
    }

    public EventLoopGroup createEventLoopGroup(int threadsCount) {
        return this.serverBootstrapFactory.isEpollEnabled() ?
                new EpollEventLoopGroup(threadsCount) :
                new NioEventLoopGroup(threadsCount);
    }
}
