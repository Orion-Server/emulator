package Orion.Game.Room.Factory;

import Orion.Api.Server.Game.Room.Data.Model.Enum.RoomTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Storage.Repository.Room.IRoomRepository;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Data.Model.CustomRoomModel;
import Orion.Game.Room.Data.Model.RoomModel;
import Orion.Game.Room.Data.Model.RoomModelData;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicReference;

@Singleton
public class RoomModelFactory {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IRoomManager roomManager;

    @Inject
    private IRoomRepository roomRepository;

    public IRoomModel createAndParseModel(IRoomModelData roomModelData) {
        try {
            final String[] heightmapRows = roomModelData.getHeightMap()
                    .replace("\n", "")
                    .split("\r");

            if (heightmapRows.length == 0) return null;

            int mapSize = 0;
            int mapSizeX = heightmapRows[0].length();
            int mapSizeY = heightmapRows.length;

            final int[][] tileHeights = new int[mapSizeX][mapSizeY];
            final RoomTileState[][] tileStates = new RoomTileState[mapSizeX][mapSizeY];

            for (int y = 0; y < mapSizeY; y++) {
                this.parseHeightmapRow(heightmapRows[y], tileHeights, tileStates, roomModelData, y, mapSize);
            }

            final int doorZ = tileHeights[roomModelData.getDoorX()][roomModelData.getDoorY()];
            final String map = concatenateHeightmap(heightmapRows);

            if(!roomModelData.getName().startsWith("custom_")) {
                return new RoomModel(roomModelData, mapSize, tileStates, map, tileHeights, doorZ);
            }

            // Do things with custom room models

            return new CustomRoomModel(roomModelData, mapSize, tileStates, map, tileHeights, doorZ);
        } catch (Exception e) {
            this.logger.error(STR."Failed to create model: \{roomModelData.getName()}");
        }

        return null;
    }

    private void parseHeightmapRow(
            final String heightmapRow,
            final int[][] tileHeights,
            final RoomTileState[][] tileStates,
            final IRoomModelData roomModelData,
            final int y,
            int mapSize
    ) {
        if(heightmapRow.isEmpty() || heightmapRow.equalsIgnoreCase("\r")) return;

        for (int x = 0; x < heightmapRow.length(); x++) {
            final char tile = heightmapRow.toCharArray()[x];
            final boolean isDoor = x == roomModelData.getDoorX() && y == roomModelData.getDoorY();

            tileStates[x][y] = !String.valueOf(tile).equalsIgnoreCase("x") || isDoor
                    ? RoomTileState.VALID : RoomTileState.INVALID;

            tileHeights[x][y] = this.getTileHeight(tile);

            mapSize++;
        }
    }

    private String concatenateHeightmap(String[] heightmapRows) {
        final StringBuilder mapBuilder = new StringBuilder();

        for (String mapLine : heightmapRows) {
            if (!mapLine.isEmpty()) {
                mapBuilder.append(mapLine).append((char) 13);
            }
        }

        return mapBuilder.toString();
    }

    private int getTileHeight(char tile) {
        if (Character.isDigit(tile)) {
            return Character.getNumericValue(tile);
        }

        if (Character.isLowerCase(tile)) {
            return tile - 'a' + 10;
        }

        return 0;
    }

    public IRoomModel deepCloneFrom(IRoomModel roomModel) {
        final int[][] squareHeights = new int[roomModel.getMapSizeX()][roomModel.getMapSizeY()];
        final RoomTileState[][] squareStates = new RoomTileState[roomModel.getMapSizeX()][roomModel.getMapSizeY()];

        for (int x = 0; x < roomModel.getMapSizeX(); x++) {
            for (int y = 0; y < roomModel.getMapSizeY(); y++) {
                squareHeights[x][y] = roomModel.getSquareHeight()[x][y];
                squareStates[x][y] = roomModel.getSquareState()[x][y];
            }
        }

        return new RoomModel(
                roomModel.getData(),
                roomModel.getMapSize(),
                squareStates,
                roomModel.getMap(),
                squareHeights,
                roomModel.getDoorZ()
        );
    }

    public IRoomModel resolveRoomModelFromResult(final IConnectionResult data) throws Exception {
        if(!data.getString("override_model").equals("1")) {
            return this.roomManager.getRoomModelByName(data.getString("model"));
        }

        final AtomicReference<IRoomModelData> modelData = new AtomicReference<>();

        this.roomRepository.loadCustomRoomModel(result -> {
            if(result == null) return;

            modelData.set(new RoomModelData(result));
        }, data.getInt("id"));

        if(modelData.get() == null) {
            this.logger.error(STR."Failed to load custom room model for room: \{data.getInt("id")}");
            return null;
        }

        return this.createAndParseModel(modelData.get());
    }
}
