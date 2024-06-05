package Orion.Networking.Codec.Message.Nitro;

import Orion.Api.Networking.Message.IComposer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class NitroMessageEncoder extends MessageToMessageEncoder<IComposer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, IComposer composer, List<Object> list) {
//        final IComposer composer = composer.write();
//
//        try {
//            composer.content().setInt(0, composer.content().writerIndex() - 4);
//
//            list.add(new BinaryWebSocketFrame(composer.content()));
//        } catch(Exception e) {
//            throw new IllegalStateException(STR."Failed to encode message. Reason: \{e.getMessage()}");
//        }
    }
}
