package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.IAttributable;

import java.util.List;

public interface IRoomFloorItem extends IRoomItem, IAttributable {
    int getRotation();

    void setRotation(int rotation);

    void setAffectedPositions(List<Position> positions);

    List<Position> getAffectedPositions();
}
