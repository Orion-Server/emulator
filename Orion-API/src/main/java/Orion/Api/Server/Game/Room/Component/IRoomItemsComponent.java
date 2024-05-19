package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;
import Orion.Api.Util.Initializable;

import java.util.concurrent.ConcurrentHashMap;

public interface IRoomItemsComponent extends Initializable {
    ConcurrentHashMap<Integer, IRoomWallItem> getWallItems();

    ConcurrentHashMap<Integer, IRoomFloorItem> getFloorItems();

    ConcurrentHashMap<Integer, String> getOwnerNames();
}
