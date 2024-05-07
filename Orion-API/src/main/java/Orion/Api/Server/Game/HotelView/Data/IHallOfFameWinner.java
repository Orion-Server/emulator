package Orion.Api.Server.Game.HotelView.Data;

import Orion.Api.Util.IFillable;

public interface IHallOfFameWinner extends IFillable, Comparable<IHallOfFameWinner> {
    int getId();

    String getUsername();

    String getLook();

    int getPoints();
}
