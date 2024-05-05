package Orion.Networking.Codec.Message.Flash;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FlashMessageEncoder extends MessageToByteEncoder<IMessageComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMessageComposer messageComposer, ByteBuf byteBuf) {
        final ByteBuf buffer = !messageComposer.isFinished()
                ? messageComposer.getBuffer()
                : channelHandlerContext.alloc().buffer();

        try {
            byteBuf.writeBytes(buffer.retain());
        } catch (Exception e) {
            throw new IllegalStateException(STR."Failed to encode message. Reason: \{e.getMessage()}");
        } finally {
            buffer.release();
        }
    }
}
