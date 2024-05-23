package Orion.Game.Room.Object.Item.Data;

import Orion.Api.Server.Game.Room.Object.Item.Data.ILimitedEditionData;
import Orion.Api.Server.Game.Room.Object.Item.Data.IRoomItemData;
import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Storage.Result.IConnectionResult;

public class RoomItemData implements IRoomItemData {
    private int id;

    private int ownerId;

    private int itemId;

    private Position position;

    private int rotation;

    private String wallPosition;

    private String extraData;

    private String wiredData;

    private ILimitedEditionData limitedData;

    private int groupId;

    private String ownerName;

    public RoomItemData(final IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getOwnerId() {
        return this.ownerId;
    }

    @Override
    public int getItemId() {
        return this.itemId;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int getRotation() {
        return this.rotation;
    }

    @Override
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    @Override
    public String getWallPosition() {
        return this.wallPosition;
    }

    @Override
    public String getExtraData() {
        return this.extraData;
    }

    @Override
    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    @Override
    public String getWiredData() {
        return this.wiredData;
    }

    @Override
    public ILimitedEditionData getLimitedData() {
        return this.limitedData;
    }

    @Override
    public int getGroupId() {
        return this.groupId;
    }

    @Override
    public String getOwnerName() {
        return this.ownerName;
    }

    @Override
    public void fill(final IConnectionResult data) throws Exception {
        this.id = data.getInt("id");
        this.ownerId = data.getInt("user_id");
        this.itemId = data.getInt("item_id");
        this.wallPosition = data.getString("wall_pos");
        this.rotation = data.getInt("rot");
        this.extraData = data.getString("extra_data");
        this.wiredData = data.getString("wired_data");
        this.groupId = data.getInt("guild_id");
        this.ownerName = data.getString("owner_name");

        if(this.wallPosition.isEmpty()) {
            this.position = new Position(data.getInt("x"), data.getInt("y"), data.getDouble("z"));
        } else {
            this.position = Position.ZERO;
        }

        if (!data.getString("limited_data").equalsIgnoreCase("0:0")) {
            this.limitedData = new LimitedEditionData(data.getString("limited_data"));
        }
    }
}
