package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.IAttributable;

import java.util.List;

public interface IRoomFloorItem extends IRoomItem, IAttributable {
    void setAffectedPositions(List<Position> positions);

    List<Position> getAffectedPositions();

    void sendUpdate();
}
