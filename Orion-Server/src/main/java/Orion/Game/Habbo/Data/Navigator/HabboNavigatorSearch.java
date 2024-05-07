package Orion.Game.Habbo.Data.Navigator;

import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Writer.Habbo.Navigator.HabboNavigatorSearchWriter;

public class HabboNavigatorSearch implements IHabboNavigatorSearch {
    private String searchCode;
    private String filter;
    private int id;

    public HabboNavigatorSearch(IConnectionResult result) {
        try {
            this.fill(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getSearchCode() {
        return this.searchCode;
    }

    @Override
    public String getFilter() {
        return this.filter;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public void write(final IMessageComposer composer) {
        HabboNavigatorSearchWriter.write(this, composer);
    }

    @Override
    public void fill(IConnectionResult result) throws Exception {
        this.id = result.getInt("id");
        this.searchCode = result.getString("search_code");
        this.filter = !result.isNull("filter") ? result.getString("filter") : "";
    }
}
