package Orion.Api.Server.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.ModelTileState;

public interface IRoomModel {
    IRoomModelData getData();

    int getDoorZ();

    int getMapSize();

    int getMapSizeX();

    int getMapSizeY();

    ModelTileState[][] getSquareState();

    int[][] getSquareHeight();

    String getMap();
}
