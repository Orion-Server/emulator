package Orion.Networking.Codec.Logger;

import Orion.Networking.Message.MessageEvent;
import com.google.inject.Singleton;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Singleton
@ChannelHandler.Sharable
public class MessageEventLogger extends MessageToMessageDecoder<MessageEvent> {
    private final Logger logger = LogManager.getLogger();

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent, List<Object> list) {
        this.logger.trace(STR."[\{messageEvent.getId()}]");

        list.add(messageEvent);
    }
}
