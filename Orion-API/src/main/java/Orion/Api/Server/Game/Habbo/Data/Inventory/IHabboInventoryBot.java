package Orion.Api.Server.Game.Habbo.Data.Inventory;

import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Api.Util.IFillable;

import java.util.ArrayList;

public interface IHabboInventoryBot extends IFillable {
    ArrayList<String> getChatLines();

    int getId();

    String getName();

    String getMotto();

    String getFigure();

    HabboGender getGender();

    int getOwnerId();

    String getOwnerName();

    boolean isChatAuto();

    boolean isChatRandom();

    double getChatDelay();

    int getChatTimeOut();

    int getChatTimestamp();

    short getLastChatIndex();

    int getBubble();

    String getType();

    int getEffect();

    boolean isCanWalk();
}
