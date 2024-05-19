package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;

public class CustomRoomModel extends RoomModel {
    public CustomRoomModel(
            IRoomModelData data,
            int mapSize,
            RoomTileState[][] tileStates,
            String roomMap,
            int[][] tileHeights,
            int doorZ
    ) {
        super(data, mapSize, tileStates, roomMap, tileHeights, doorZ);
    }
}
