package Orion.Networking.Codec.Message.Nitro;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class NitroMessageEncoder extends MessageToByteEncoder<IMessageComposer> {
    @Override
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return super.acceptOutboundMessage(msg);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMessageComposer messageComposer, ByteBuf byteBuf) {
        final ByteBuf buffer = messageComposer.content();

        try {
            byteBuf.writeBytes(buffer);
        } finally {
            buffer.release();
        }
    }
}
