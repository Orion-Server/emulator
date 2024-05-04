package Orion.Networking.Codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class CrossDomainDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();

        if(byteBuf.readableBytes() < 1) return;

        if(byteBuf.readByte() != 0x3C) {
            channelHandlerContext.channel().pipeline().remove(this);
            return;
        }

        channelHandlerContext.channel().writeAndFlush("""
            <?xml version="1.0"?>
            <!DOCTYPE cross-domain-policy SYSTEM "/xml/dtds/cross-domain-policy.dtd">
            <cross-domain-policy>
            <allow-access-from domain="*" to-ports="*" />
            </cross-domain-policy>\0
        """).addListener(ChannelFutureListener.CLOSE);
    }
}
