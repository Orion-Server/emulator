package Orion.Networking.Codec.Message.Nitro;

import Orion.Networking.Message.MessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

public class NitroMessageDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        try {
            final ByteBuf buffer = webSocketFrame.content();

            if (buffer.readableBytes() < 4) return;

            buffer.markReaderIndex();

            final int length = buffer.readInt();

            if (buffer.readableBytes() < length) {
                buffer.resetReaderIndex();
                return;
            }

            if(length < 0) return;

            list.add(new MessageEvent(length, buffer.readBytes(length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
