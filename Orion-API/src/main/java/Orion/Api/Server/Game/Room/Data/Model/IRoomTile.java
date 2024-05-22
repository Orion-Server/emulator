package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Item.IRoomFloorItem;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItem;
import Orion.Api.Server.Game.Room.Object.Pathfinder.RoomEntityMovementNode;
import Orion.Api.Server.Game.Room.Object.Pathfinder.RoomTileStatusType;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.IPositionable;
import Orion.Api.Util.Initializable;

import java.util.Set;
import java.util.function.Consumer;

public interface IRoomTile extends IPositionable, Initializable, IDisposable {
    double getStackHeight();

    void onEntityLeave(IRoomEntity entity);

    void onEntityEnter(IRoomEntity entity);

    double getWalkHeight();

    void addItem(IRoomFloorItem item);

    RoomEntityMovementNode getMovementNode();

    boolean canPlaceItems();

    boolean canStack();

    RoomTileStatusType getStatusType();

    RoomTileState getState();

    IRoomItem getTopItem();

    Position getRedirectTo();

    Set<IRoomEntity> getEntities();

    void scheduleEvent(int entityId, Consumer<IRoomEntity> event);

    void clearScheduledEvent(int entityId);
}
