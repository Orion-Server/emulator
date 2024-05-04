package Orion.Networking.Message.Handler;

import Orion.Api.Networking.Session.ISession;
import Orion.Api.Networking.Session.ISessionManager;
import Orion.Api.Protocol.IServerMessageHandler;
import Orion.Networking.Message.MessageEvent;
import Orion.Networking.Session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.ChannelInputShutdownEvent;

public class NitroMessageHandler extends SimpleChannelInboundHandler<MessageEvent> {
    private final ISessionManager sessionManager;

    private final IServerMessageHandler serverMessageHandler;

    public NitroMessageHandler(
            final ISessionManager sessionManager,
            final IServerMessageHandler serverMessageHandler
    ) {
        super();

        this.sessionManager = sessionManager;
        this.serverMessageHandler = serverMessageHandler;
    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) {
        final ISession session = channelHandlerContext.channel().attr(SessionManager.SESSION_KEY).get();

        if(session == null) {
            channelHandlerContext.disconnect();
            return;
        }

        session.disconnect();
        sessionManager.removeSession(session);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object event) {
        if(event instanceof ChannelInputShutdownEvent) {
            channelHandlerContext.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        if(!channelHandlerContext.channel().isActive()) return;

        channelHandlerContext.close();
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
