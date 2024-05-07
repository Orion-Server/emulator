package Orion.Api.Server.Game.HotelView;

import Orion.Api.Server.Game.HotelView.Data.IArticleWidgetList;
import Orion.Api.Server.Game.HotelView.Data.IHallOfFame;
import Orion.Api.Util.Initializable;

public interface IHotelViewManager extends Initializable {
    IHallOfFame getHallOfFame();

    IArticleWidgetList getArticleWidgetList();
}
