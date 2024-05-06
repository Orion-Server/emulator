package Orion.Api.Server.Game.Habbo.Data.Messenger;

import Orion.Api.Util.IFillable;

public interface IMessengerFriendRequest extends IFillable {
    String getLook();

    String getUsername();

    int getId();
}
