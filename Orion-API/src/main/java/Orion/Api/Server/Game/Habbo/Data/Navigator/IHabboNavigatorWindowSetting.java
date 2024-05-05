package Orion.Api.Server.Game.Habbo.Data.Navigator;

import Orion.Api.Util.IFillable;

public interface IHabboNavigatorWindowSetting extends IFillable {
    int getWindowX();

    int getWindowY();

    int getWindowWidth();

    int getWindowHeight();

    boolean isLeftPanelCollapsed();

    int getResultsMode();
}
