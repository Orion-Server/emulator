package Orion.Networking.Codec.Message.Nitro;

import Orion.Api.Networking.Message.IMessageComposer;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;

import java.util.List;

public class NitroMessageEncoder extends MessageToMessageEncoder<IMessageComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IMessageComposer messageComposer, List<Object> list) {
        final ByteBufHolder composer = messageComposer.copy();

        try {
            composer.content().setInt(0, composer.content().writerIndex() - 4);

            list.add(new BinaryWebSocketFrame(composer.content()));
        } catch(Exception e) {
            throw new IllegalStateException(STR."Failed to encode message. Reason: \{e.getMessage()}");
        }
    }
}
