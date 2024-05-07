package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Util.IFillable;

public interface IRoomModelData extends IFillable {
    int getDoorX();

    int getDoorY();

    String getName();

    String getHeightMap();

    int getDoorDirection();
}
