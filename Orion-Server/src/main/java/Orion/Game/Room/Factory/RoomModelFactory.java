package Orion.Game.Room.Factory;

import Orion.Api.Server.Game.Room.Data.Model.Enum.ModelTileState;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;
import Orion.Game.Room.Data.Model.RoomModel;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class RoomModelFactory {
    private final Logger logger = LogManager.getLogger();

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
            final ModelTileState[][] tileStates = new ModelTileState[mapSizeX][mapSizeY];

            for (int y = 0; y < mapSizeY; y++) {
                this.parseHeightmapRow(heightmapRows[y], tileHeights, tileStates, roomModelData, y, mapSize);
            }

            final int doorZ = tileHeights[roomModelData.getDoorX()][roomModelData.getDoorY()];
            final String map = concatenateHeightmap(heightmapRows);

            return new RoomModel(roomModelData, mapSize, tileStates, map, tileHeights, doorZ);
        } catch (Exception e) {
            this.logger.error(STR."Failed to create model: \{roomModelData.getName()}");
        }

        return null;
    }

    private void parseHeightmapRow(
            final String heightmapRow,
            final int[][] tileHeights,
            final ModelTileState[][] tileStates,
            final IRoomModelData roomModelData,
            final int y,
            int mapSize
    ) {
        if(heightmapRow.isEmpty() || heightmapRow.equalsIgnoreCase("\r")) return;

        for (int x = 0; x < heightmapRow.length(); x++) {
            final char tile = heightmapRow.toCharArray()[x];

            if (String.valueOf(tile).equals("x")) {
                tileStates[x][y] = (x == roomModelData.getDoorX() && y == roomModelData.getDoorY()) ? ModelTileState.VALID : ModelTileState.INVALID;
                continue;
            }

            tileStates[x][y] = ModelTileState.VALID;
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
        final ModelTileState[][] squareStates = new ModelTileState[roomModel.getMapSizeX()][roomModel.getMapSizeY()];

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
}
