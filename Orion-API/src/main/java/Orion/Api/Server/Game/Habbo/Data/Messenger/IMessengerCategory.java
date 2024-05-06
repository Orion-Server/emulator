package Orion.Api.Server.Game.Habbo.Data.Messenger;

import Orion.Api.Util.IFillable;

public interface IMessengerCategory extends IFillable {
    String getName();

    int getUserId();

    int getId();
}
