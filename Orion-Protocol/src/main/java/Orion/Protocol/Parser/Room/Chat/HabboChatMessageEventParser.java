package Orion.Protocol.Parser.Room.Chat;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;

public class HabboChatMessageEventParser implements IEventParser {
    public String message;
    private int bubbleId;

    @Override
    public int getId() {
        return EventHeaders.HabboChatMessageEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.message = messageEvent.readString();
        this.bubbleId = messageEvent.readInt();
    }
}
