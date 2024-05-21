package Orion.Networking.Codec.Message.Nitro;

import Orion.Networking.Message.MessageEvent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

public class NitroMessageDecoder extends MessageToMessageDecoder<WebSocketFrame> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) {
        try {
            final ByteBuf wsBuffer = webSocketFrame.content();

            if (wsBuffer.readableBytes() < 4) return;

            wsBuffer.markReaderIndex();

            final int length = wsBuffer.readInt();

            if (wsBuffer.readableBytes() < length) {
                wsBuffer.resetReaderIndex();
                return;
            }

            if(length < 0) return;

            list.add(new MessageEvent(length, wsBuffer.readBytes(length)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
