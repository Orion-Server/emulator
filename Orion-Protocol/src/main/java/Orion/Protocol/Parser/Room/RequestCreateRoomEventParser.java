package Orion.Protocol.Parser.Room;

import Orion.Api.Networking.Message.IMessageEvent;
import Orion.Api.Protocol.Parser.IEventParser;
import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Api.Server.Game.Room.Enums.RoomTradeType;
import Orion.Protocol.Message.Event.EventHeaders;
import com.google.inject.Singleton;

@Singleton
public class RequestCreateRoomEventParser implements IEventParser {
    public String name;
    public String description;
    public String modelName;
    public RoomCategoryType categoryId;
    public int maxUsers;
    public RoomTradeType tradeType;

    @Override
    public int getId() {
        return EventHeaders.RequestCreateRoomEvent;
    }

    @Override
    public void parse(IMessageEvent messageEvent) {
        this.name = messageEvent.readString();
        this.description = messageEvent.readString();
        this.modelName = messageEvent.readString();
        this.categoryId = RoomCategoryType.fromCategoryId(messageEvent.readInt());
        this.maxUsers = messageEvent.readInt();
        this.tradeType = RoomTradeType.fromValue(messageEvent.readInt());
    }
}
