package Orion.Game.Room.Data;

import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Room.Data.IRoomCategory;
import Orion.Api.Storage.Result.IConnectionResult;

public class RoomCategory implements IRoomCategory {
    private int id;
    private int minRank;
    private String caption;
    private String captionSave;
    private boolean canTrade;
    private int maxUserCount;
    private boolean official;
    private NavigatorDisplayMode displayMode;
    private int order;

    public RoomCategory(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public int getMinRank() {
        return this.minRank;
    }

    public String getCaption() {
        return this.caption;
    }

    public String getCaptionSave() {
        return this.captionSave;
    }

    public boolean isCanTrade() {
        return this.canTrade;
    }

    public int getMaxUserCount() {
        return this.maxUserCount;
    }

    public boolean isPublic() {
        return this.official;
    }

    public NavigatorDisplayMode getDisplayMode() {
        return this.displayMode;
    }

    public int getOrder() {
        return this.order;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.id = result.getInt("id");
        this.minRank = result.getInt("min_rank");
        this.caption = result.getString("caption");
        this.captionSave = result.getString("caption_save");
        this.canTrade = result.getBoolean("can_trade");
        this.maxUserCount = result.getInt("max_user_count");
        this.official = result.getString("public").equals("1");
        this.displayMode = NavigatorDisplayMode.fromType(result.getInt("list_type"));
        this.order = result.getInt("order_num");
    }

    @Override
    public int compareTo(IRoomCategory roomCategory) {
        return roomCategory.getId() - this.getId();
    }
}

