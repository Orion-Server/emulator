package Orion.Protocol.Parser.Hanshake;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class ClientHelloEventParser implements IEventParser {
    public String clientVersion;
    public String type;
    public int plataform;
    public int category;

    @Override
    public int getId() {
        return EventHeaders.ClientHelloEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.clientVersion = messageEvent.readString();
        this.type = messageEvent.readString();
        this.plataform = messageEvent.readInt();
        this.category = messageEvent.readInt();
    }
}
