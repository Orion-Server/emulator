package Orion.Api.Server.Game.Room.Object.Item;

import Orion.Api.Util.IAttributable;

public interface IRoomFloorItem extends IRoomItem, IAttributable {
    int getRotation();

    void setRotation(int rotation);
}
