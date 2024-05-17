package Orion.Networking.Codec.Message.Flash;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FlashMessageEncoder extends MessageToByteEncoder<IMessageComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMessageComposer messageComposer, ByteBuf byteBuf) {
        final ByteBufHolder buffer = messageComposer.copy();

        try {
            buffer.content().setInt(0, buffer.content().writerIndex() - 4);

            byteBuf.writeBytes(buffer.content());
        } catch (Exception e) {
            throw new IllegalStateException(STR."Failed to encode message. Reason: \{e.getMessage()}");
        } finally {
            buffer.release();
        }
    }
}
