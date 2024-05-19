package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;

public class RoomModel implements IRoomModel {
    private final IRoomModelData data;

    private final RoomTileState[][] squareStates;
    private final int[][] squareHeights;
    private final String roomMap;
    private final int doorZ;

    private final int mapSize;
    private final int mapSizeX;
    private final int mapSizeY;

    public RoomModel(
            final IRoomModelData data,
            int mapSize,
            RoomTileState[][] tileStates,
            String roomMap,
            int[][] tileHeights,
            int doorZ
    ) {
        this.mapSize = mapSize;
        this.data = data;
        this.squareStates = tileStates;
        this.roomMap = roomMap;
        this.squareHeights = tileHeights;
        this.doorZ = doorZ;

        this.mapSizeX = tileHeights.length;
        this.mapSizeY = tileHeights[0].length;
    }

    public IRoomModelData getData() {
        return this.data;
    }

    @Override
    public int getDoorZ() {
        return this.doorZ;
    }

    @Override
    public int getMapSizeX() {
        return this.mapSizeX;
    }

    @Override
    public int getMapSize() {
        return this.mapSize;
    }

    @Override
    public int getMapSizeY() {
        return this.mapSizeY;
    }

    @Override
    public RoomTileState[][] getSquareState() {
        return this.squareStates;
    }

    @Override
    public int[][] getSquareHeight() {
        return this.squareHeights;
    }

    @Override
    public String getMap() {
        return this.roomMap;
    }
}
