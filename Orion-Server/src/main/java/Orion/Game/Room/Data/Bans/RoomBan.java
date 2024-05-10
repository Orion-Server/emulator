package Orion.Game.Room.Data.Bans;

import Orion.Api.Server.Game.Room.Data.Ban.IRoomBan;
import Orion.Api.Storage.Result.IConnectionResult;

public class RoomBan implements IRoomBan {
    public int roomId;
    public int userId;
    public String username;
    public long endTimestamp;

    public RoomBan(IConnectionResult result) {
        try {
            this.fill(result);
        } catch (Exception e) {
            System.out.println(STR."Error while filling room ban data: \{e.getMessage()}");
        }
    }

    @Override
    public int getRoomId() {
        return this.roomId;
    }

    @Override
    public int getUserId() {
        return this.userId;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public long getEndTimestamp() {
        return this.endTimestamp;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.roomId = result.getInt("room_id");
        this.userId = result.getInt("user_id");
        this.username = result.getString("username");
        this.endTimestamp = result.getInt("ends");
    }
}
