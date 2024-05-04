package Orion.Networking.Session.Handler;

import Orion.Networking.Message.MessageEvent;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class FlashSessionHandler extends SimpleChannelInboundHandler<MessageEvent> {
    @Override
    public void channelActive(ChannelHandlerContext channel) {
        System.out.println("Channel active");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        System.out.println(messageEvent.getId());
    }
}
