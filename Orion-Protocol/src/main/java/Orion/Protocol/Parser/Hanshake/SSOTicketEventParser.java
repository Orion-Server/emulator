package Orion.Protocol.Parser.Hanshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class SSOTicketEventParser implements IEventParser {
    public String ticket;

    @Override
    public int getId() {
        return EventHeaders.SSOTicketEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.ticket = messageEvent.readString().replaceAll(" ", "");
    }
}
