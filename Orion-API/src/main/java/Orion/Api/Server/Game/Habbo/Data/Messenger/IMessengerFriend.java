package Orion.Api.Server.Game.Habbo.Data.Messenger;

import Orion.Api.Server.Game.Habbo.Enums.HabboGender;
import Orion.Api.Util.IFillable;

public interface IMessengerFriend extends IFillable {
    int getId();

    String getUsername();

    HabboGender getGender();

    int getOnline();

    String getLook();

    String getLookIfAvailable();

    String getMotto();

    short getRelationType();

    int getCategoryId();

    int getUserOne();

    boolean isInRoom();
}
