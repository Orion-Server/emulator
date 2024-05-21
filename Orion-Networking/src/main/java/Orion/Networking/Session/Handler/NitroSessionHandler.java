package Orion.Networking.Session.Handler;

import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Networking.Codec.Message.Nitro.NitroMessageDecoder;
import Orion.Networking.Message.Handler.NitroMessageHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.ReferenceCountUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NitroSessionHandler extends ChannelInboundHandlerAdapter {
    private final Logger logger = LogManager.getLogger();

    private final ISessionManager sessionManager;

    private final IServerMessageHandler serverMessageHandler;

    public NitroSessionHandler(
            final ISessionManager sessionManager,
            final IServerMessageHandler serverMessageHandler
    ) {
        super();

        this.sessionManager = sessionManager;
        this.serverMessageHandler = serverMessageHandler;
    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object message) {
        try {
            if(!(message instanceof FullHttpRequest httpRequest)) {
                this.logger.error(STR."[NitroSessionHandler] Received an invalid message type: \{message.getClass().getSimpleName()}");
                return;
            }

            final HttpHeaders headers = httpRequest.headers();

            if(headers.isEmpty()) {
                this.logger.error(STR."[NitroSessionHandler] Received an empty header.");
                return;
            }

            final String connectionHeader = headers.get(HttpHeaderNames.CONNECTION.toString());
            final String upgradeHeader = headers.get(HttpHeaderNames.UPGRADE.toString());

            if("Upgrade".equalsIgnoreCase(connectionHeader) || "websocket".equalsIgnoreCase(upgradeHeader)) {
                this.handleWebSocketRequest(channelHandlerContext, httpRequest, headers);
            }
        } finally {
            ReferenceCountUtil.release(message);
        }
    }

    private void handleWebSocketRequest(
            ChannelHandlerContext channelHandlerContext,
            final HttpRequest httpRequest,
            final HttpHeaders headers
    ) {
        String ipAddress = "0.0.0.0";

        if (headers.contains("X-Forwarded-For")) {
            ipAddress = headers.get("X-Forwarded-For");
        }

        if (headers.contains("X-Real-IP")) {
            ipAddress = headers.get("X-Real-IP");
        }

        if (headers.contains("CF-Connecting-IP")) {
            ipAddress = headers.get("CF-Connecting-IP");
        }

        final boolean session = this.sessionManager.addChannel(channelHandlerContext, ipAddress);

        if(!session) {
            this.logger.warn(STR."[NitroSessionHandler] Failed to create a session: \{ipAddress}");

            channelHandlerContext.disconnect();
            return;
        }

        channelHandlerContext.pipeline()
                .remove(this)
                .addLast(new NitroMessageDecoder())
                .addLast(new NitroMessageHandler(this.sessionManager, this.serverMessageHandler));

        this.attemptHandshake(channelHandlerContext, httpRequest);
    }

    private void attemptHandshake(ChannelHandlerContext channelHandlerContext, HttpRequest httpRequest) {
        final WebSocketServerHandshakerFactory serverHandshakerFactory = new WebSocketServerHandshakerFactory(
                null, null, true
        );

        final WebSocketServerHandshaker serverHandshaker = serverHandshakerFactory.newHandshaker(httpRequest);

        if(serverHandshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channelHandlerContext.channel());
            return;
        }

        serverHandshaker.handshake(channelHandlerContext.channel(), httpRequest);
    }
}
