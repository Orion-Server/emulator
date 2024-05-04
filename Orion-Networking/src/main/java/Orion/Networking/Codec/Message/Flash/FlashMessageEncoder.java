package Orion.Networking.Codec.Message.Flash;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FlashMessageEncoder extends MessageToByteEncoder<IMessageComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMessageComposer messageComposer, ByteBuf byteBuf) throws Exception {
        final ByteBuf buffer = messageComposer.content();

        try {
            byteBuf.writeBytes(buffer);
        } finally {
            buffer.release();
        }
    }
}
