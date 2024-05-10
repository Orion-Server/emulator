package Orion.Game.Room.Data.Model;

import Orion.Api.Server.Game.Room.Data.Model.IRoomModelData;
import Orion.Api.Storage.Result.IConnectionResult;

public class RoomModelData implements IRoomModelData {
    private String name;
    private String heightMap;
    private int doorX;
    private int doorY;
    private int doorDirection;

    public RoomModelData(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getDoorX() {
        return this.doorX;
    }

    @Override
    public int getDoorY() {
        return this.doorY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getHeightMap() {
        return this.heightMap;
    }

    @Override
    public String getRelativeHeightMap() {
        return this.heightMap.replace("\r\n", "\r");
    }

    @Override
    public int getDoorDirection() {
        return this.doorDirection;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.doorX = result.getInt("door_x");
        this.doorY = result.getInt("door_y");
        this.name = result.getString("name");
        this.heightMap = result.getString("heightmap");
        this.doorDirection = result.getInt("door_dir");
    }
}
