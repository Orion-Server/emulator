package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Room.Object.Item.Enum.FurnitureMovementError;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomWallItem;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.Initializable;

import java.util.concurrent.ConcurrentHashMap;

public interface IRoomItemsComponent extends Initializable {
    ConcurrentHashMap<Integer, IRoomWallItem> getWallItems();

    ConcurrentHashMap<Integer, IRoomFloorItem> getFloorItems();

    IRoomFloorItem getFloorItemByVirtualId(int virtualId);

    ConcurrentHashMap<Integer, String> getOwnerNames();

    IRoomWallItem getWallItemByVirtualId(int virtualId);

    FurnitureMovementError moveFloorItem(final IRoomFloorItem item, Position position, int rotation);
}
