package Orion.Api.Server.Game.Room.Data;

import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Util.IFillable;

public interface IRoomCategory extends IFillable, Comparable<IRoomCategory> {
    int getId();

    int getMinRank();

    String getCaption();

    String getCaptionSave();

    boolean isCanTrade();

    int getMaxUserCount();

    boolean isPublic();

    NavigatorDisplayMode getDisplayMode();

    int getOrder();
}
