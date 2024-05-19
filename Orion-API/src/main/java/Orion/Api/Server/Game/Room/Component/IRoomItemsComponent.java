package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItem;
import Orion.Api.Util.Initializable;

import java.util.concurrent.ConcurrentHashMap;

public interface IRoomItemsComponent extends Initializable {
    ConcurrentHashMap<Integer, IRoomItem> getWallItems();

    ConcurrentHashMap<Integer, IRoomFloorItem> getFloorItems();

    ConcurrentHashMap<Integer, String> getOwnerNames();
}
