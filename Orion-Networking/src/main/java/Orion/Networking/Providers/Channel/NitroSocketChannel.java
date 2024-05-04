package Orion.Networking.Providers.Channel;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Networking.Codec.Message.Nitro.NitroMessageEncoder;
import Orion.Networking.Session.Handler.NitroSessionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

public class NitroSocketChannel extends ChannelInitializer<SocketChannel> {
    private final ISessionManager sessionManager;

    private final IServerMessageHandler serverMessageHandler;

    public NitroSocketChannel(
            final ISessionManager sessionManager,
            final IServerMessageHandler serverMessageHandler
    ) {
        this.sessionManager = sessionManager;
        this.serverMessageHandler = serverMessageHandler;
    }

    @Override
    protected void initChannel(final SocketChannel socketChannel) {
        final ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new WebSocketServerCompressionHandler())
                .addLast(new NitroMessageEncoder())
                .addLast(new NitroSessionHandler(this.sessionManager, this.serverMessageHandler));

        socketChannel.config().setTrafficClass(24);
        socketChannel.config().setTcpNoDelay(true);
    }
}
