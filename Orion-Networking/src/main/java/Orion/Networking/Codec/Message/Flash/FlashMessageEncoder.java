package Orion.Networking.Codec.Message.Flash;

import Orion.Api.Networking.Message.IComposer;
import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class FlashMessageEncoder extends MessageToByteEncoder<IComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IComposer composer, ByteBuf byteBuf) {
        try {
            final IMessageComposer messageComposer = composer.write(byteBuf);

            messageComposer.setBufferHeader();
        } catch (Exception e) {
            throw new IllegalStateException(STR."Failed to encode message. Reason: \{e.getMessage()}");
        }
    }
}
