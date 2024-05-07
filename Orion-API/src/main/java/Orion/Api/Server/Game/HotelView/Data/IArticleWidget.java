package Orion.Api.Server.Game.HotelView.Data;

import Orion.Api.Util.IFillable;

public interface IArticleWidget extends IFillable {
    int getId();

    String getTitle();

    String getText();

    String getButtonText();

    int getType();

    String getLink();

    String getImage();
}
