package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;

public interface IRoomModel {
    IRoomModelData getData();

    int getDoorZ();

    int getMapSize();

    int getMapSizeX();

    int getMapSizeY();

    RoomTileState[][] getSquareState();

    int[][] getSquareHeight();

    String getMap();
}
