package Orion.Networking.Codec.Message.Flash;

import Orion.Networking.Message.MessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class FlashMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        try {
            if (byteBuf.readableBytes() < 6) return;

            byteBuf.markReaderIndex();

            final int length = byteBuf.readInt();

            if (byteBuf.readableBytes() < length) {
                byteBuf.resetReaderIndex();
                return;
            }

            if(length < 0) return;

            list.add(new MessageEvent(length, byteBuf.readBytes(length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
