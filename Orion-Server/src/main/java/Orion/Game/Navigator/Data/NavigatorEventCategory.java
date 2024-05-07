package Orion.Game.Navigator.Data;

import Orion.Api.Server.Game.Navigator.Data.INavigatorEventCategory;

public class NavigatorEventCategory implements INavigatorEventCategory {
    private int id;
    private String name;
    private boolean isVisible;

    public NavigatorEventCategory(String data) {
        try {
            this.fill(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    private void fill(String result) throws Exception {
        final String[] data = result.split(",");

        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.isVisible = data[2].equalsIgnoreCase("true");
    }
}
