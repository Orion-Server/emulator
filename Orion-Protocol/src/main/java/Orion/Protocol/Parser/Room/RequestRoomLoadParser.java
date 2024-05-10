package Orion.Protocol.Parser.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;

public class RequestRoomLoadParser implements IEventParser {
    public int roomId;
    public String password;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomLoadEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.roomId = messageEvent.readInt();
        this.password = messageEvent.readString();
    }
}
