package Orion.Networking.Providers.Channel;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Networking.Session.Throttle.IAddressAttempt;
import Orion.Networking.Codec.CrossDomainDecoder;
import Orion.Networking.Codec.Message.Flash.FlashMessageDecoder;
import Orion.Networking.Codec.Message.Flash.FlashMessageEncoder;
import Orion.Networking.Session.Handler.FlashSessionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlashSocketChannel extends ChannelInitializer<SocketChannel> {
    private final Logger logger = LogManager.getLogger();

    private final EventExecutorGroup executorGroup;

    private final ISessionManager sessionManager;

    private final FlashSessionHandler sessionHandler;

    public FlashSocketChannel(
            final ISessionManager sessionManager,
            final EventLoopGroup eventLoopGroupFactory
    ) {
        this.sessionManager = sessionManager;
        this.executorGroup = eventLoopGroupFactory;

        this.sessionHandler = new FlashSessionHandler();
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        final String ip = socketChannel.remoteAddress().getAddress().getHostAddress();

        this.sessionManager.createOrUpdateConnectionAttempt(ip);

        final IAddressAttempt addressAttempt = this.sessionManager.getConnectionAttempt(ip);

        if (addressAttempt != null && addressAttempt.shouldBlockConnection()) {
            this.logger.warn(STR."Connection attempt from {} has been blocked.", ip);

            socketChannel.disconnect();
            return;
        }

        socketChannel.config().setTrafficClass(0x18);

        socketChannel.pipeline()
                .addLast("crossDomainDecoder", new CrossDomainDecoder())
                .addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8))
                .addLast("messageDecoder", new FlashMessageDecoder())
                .addLast("messageEncoder", new FlashMessageEncoder());

        socketChannel.pipeline().addLast(this.executorGroup, "sessionHandler", this.sessionHandler);
    }
}
