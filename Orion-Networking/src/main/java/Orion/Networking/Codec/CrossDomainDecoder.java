package Orion.Networking.Codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class CrossDomainDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        byteBuf.markReaderIndex();

        byte b = byteBuf.readByte();

        if (b == '<') {
            byteBuf.resetReaderIndex();
            channelHandlerContext.channel().writeAndFlush(Unpooled.copiedBuffer(
                    "<?xml version=\"1.0\"?>\n"
                            + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\n"
                            + "<cross-domain-policy>\n"
                            + "<allow-access-from domain=\"*\" to-ports=\"1-31111\" />\n"
                            + "</cross-domain-policy>" + (char) 0
                    , CharsetUtil.UTF_8)).addListener(ChannelFutureListener.CLOSE);
            return;
        }

        channelHandlerContext.pipeline().remove(this);
        byteBuf.resetReaderIndex();
    }
}
