package Orion.Game.Habbo.Data.Navigator;

import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorWindowSetting;
import Orion.Api.Storage.Result.IConnectionResult;

public class HabboNavigatorWindowSetting implements IHabboNavigatorWindowSetting {
    private int userId = -1;
    private int windowX = 100;
    private int windowY = 100;
    private int windowWidth = 425;
    private int windowHeight = 535;
    private boolean leftPanelCollapsed = false;
    public int resultsMode = 0;

    public HabboNavigatorWindowSetting(IConnectionResult result) {
        try {
            this.fillOrCreate(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getWindowX() {
        return this.windowX;
    }

    @Override
    public int getWindowY() {
        return this.windowY;
    }

    @Override
    public int getWindowWidth() {
        return this.windowWidth;
    }

    @Override
    public int getWindowHeight() {
        return this.windowHeight;
    }

    @Override
    public boolean isLeftPanelCollapsed() {
        return this.leftPanelCollapsed;
    }

    @Override
    public int getResultsMode() {
        return this.resultsMode;
    }

    private void fillOrCreate(IConnectionResult result) throws Exception {
        this.userId = result.getInt("id");

        if(!result.hasColumn("x")) return; // TODO: Create new record in the database

        this.fill(result);
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.windowX = result.getInt("x");
        this.windowY = result.getInt("y");
        this.windowWidth = result.getInt("width");
        this.windowHeight = result.getInt("height");
        this.leftPanelCollapsed = result.getBoolean("open_searches");
    }
}
