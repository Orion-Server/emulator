package Orion.Api.Server.Game.Room.Object.Item.Data;

import Orion.Api.Server.Game.Util.Position;
import Orion.Api.Util.IFillable;

public interface IRoomItemData extends IFillable {
    int getId();

    int getOwnerId();

    int getItemId();

    Position getPosition();

    void setPosition(Position position);

    int getRotation();

    void setRotation(int rotation);

    String getWallPosition();

    String getExtraData();

    void setExtraData(String extraData);

    String getWiredData();

    ILimitedEditionData getLimitedData();

    int getGroupId();

    String getOwnerName();
}
