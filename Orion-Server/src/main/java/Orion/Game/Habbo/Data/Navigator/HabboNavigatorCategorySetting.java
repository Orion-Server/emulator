package Orion.Game.Habbo.Data.Navigator;

import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorCategorySetting;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Storage.Result.IConnectionResult;

public class HabboNavigatorCategorySetting implements IHabboNavigatorCategorySetting {
    private String caption;
    private NavigatorDisplayMode displayMode;
    private NavigatorLayoutDisplay layoutDisplay;

    public HabboNavigatorCategorySetting(IConnectionResult data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public NavigatorDisplayMode getDisplayMode() {
        return displayMode;
    }

    @Override
    public NavigatorLayoutDisplay getLayoutDisplay() {
        return layoutDisplay;
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.caption = result.getString("caption");
        this.displayMode = NavigatorDisplayMode.fromString(result.getString("list_type"));
        this.layoutDisplay = NavigatorLayoutDisplay.fromString(result.getString("display"));
    }
}
