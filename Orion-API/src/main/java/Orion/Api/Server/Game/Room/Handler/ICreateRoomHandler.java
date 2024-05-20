package Orion.Api.Server.Game.Room.Handler;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Enums.RoomCategoryType;
import Orion.Api.Server.Game.Room.Enums.RoomTradeType;
import Orion.Api.Server.Game.Room.IRoom;

public interface ICreateRoomHandler {
    IRoom createRoom(IHabbo habbo, String name, String description, String modelName, RoomCategoryType categoryId, int maxUsers, RoomTradeType tradeType);
}
