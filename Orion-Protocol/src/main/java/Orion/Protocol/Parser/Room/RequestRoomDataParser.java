package Orion.Protocol.Parser.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Protocol.Message.Event.EventHeaders;

public class RequestRoomDataParser implements IEventParser {
    public int roomId;
    public int enterRoom;
    public int forwardRoom;

    @Override
    public int getId() {
        return EventHeaders.RequestRoomDataEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.roomId = messageEvent.readInt();
        this.enterRoom = messageEvent.readInt();
        this.forwardRoom = messageEvent.readInt();
    }
}
