package Orion.Networking.Session.Handler;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Networking.Message.MessageEvent;
import Orion.Networking.Session.SessionManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@ChannelHandler.Sharable
public class FlashSessionHandler extends SimpleChannelInboundHandler<MessageEvent> {
    private final Logger logger = LogManager.getLogger();

    private final ISessionManager sessionManager;

    private final IServerMessageHandler serverMessageHandler;

    public FlashSessionHandler(
            final ISessionManager sessionManager,
            final IServerMessageHandler serverMessageHandler
    ) {
        this.sessionManager = sessionManager;
        this.serverMessageHandler = serverMessageHandler;
    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        final boolean session = this.sessionManager.addChannel(channelHandlerContext, null);

        if(session) return;

        this.logger.warn(STR."[NitroSessionHandler] Failed to create a session for the channel.");

        channelHandlerContext.disconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        if (channelHandlerContext.channel().isActive()) {
            channelHandlerContext.close();
        }

        if (cause instanceof IOException) return;

        this.logger.error("Disconnected by ExceptionCaught", cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        final ISession session = channelHandlerContext.channel().attr(SessionManager.SESSION_KEY).get();

        if(session == null) {
            channelHandlerContext.disconnect();
            return;
        }

        session.disconnect();
        this.sessionManager.removeSession(session);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object event) {
        if (event instanceof ChannelInputShutdownEvent) {
            channelHandlerContext.close();
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) {
        try {
            final ISession session = channelHandlerContext.channel().attr(SessionManager.SESSION_KEY).get();

            if(session == null) {
                channelHandlerContext.disconnect();
                return;
            }

            this.serverMessageHandler.handle(session, messageEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.flush();
    }
}
