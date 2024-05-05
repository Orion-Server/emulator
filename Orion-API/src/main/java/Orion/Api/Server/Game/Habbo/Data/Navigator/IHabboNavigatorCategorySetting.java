package Orion.Api.Server.Game.Habbo.Data.Navigator;

import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Util.IFillable;

public interface IHabboNavigatorCategorySetting extends IFillable {
    String getCaption();
    NavigatorDisplayMode getDisplayMode();
    NavigatorLayoutDisplay getLayoutDisplay();
}
