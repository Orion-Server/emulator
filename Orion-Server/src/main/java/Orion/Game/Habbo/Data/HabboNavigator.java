package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboNavigator;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorCategorySetting;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorWindowSetting;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorDisplayMode;
import Orion.Api.Server.Game.Navigator.Enums.NavigatorLayoutDisplay;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Habbo.Data.Navigator.HabboNavigatorWindowSetting;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

public class HabboNavigator implements IHabboNavigator {
    private int habboId;

    private final THashSet<IHabboNavigatorSearch> navigatorSearches;

    private final IHabboNavigatorWindowSetting navigatorWindowSettings;

    private final THashMap<String, IHabboNavigatorCategorySetting> navigatorCategoriesSettings;

    public HabboNavigator(IConnectionResult data) {
        this.navigatorSearches = new THashSet<>();
        this.navigatorCategoriesSettings = new THashMap<>();

        this.navigatorWindowSettings = new HabboNavigatorWindowSetting(data);
    }

    @Override
    public void setNavigatorSearches(THashSet<IHabboNavigatorSearch> navigatorSearches) {
        this.navigatorSearches.clear();
        this.navigatorSearches.addAll(navigatorSearches);
    }

    @Override
    public void setNavigatorCategoriesSettings(THashMap<String, IHabboNavigatorCategorySetting> navigatorCategoriesSettings) {
        this.navigatorCategoriesSettings.clear();
        this.navigatorCategoriesSettings.putAll(navigatorCategoriesSettings);
    }

    @Override
    public IHabboNavigatorWindowSetting getNavigatorWindowSettings() {
        return navigatorWindowSettings;
    }

    @Override
    public THashSet<IHabboNavigatorSearch> getNavigatorSearches() {
        return navigatorSearches;
    }

    @Override
    public IHabboNavigatorCategorySetting getNavigatorCategorySettingsFromName(String name) {
        return this.navigatorCategoriesSettings.get(name);
    }

    @Override
    public NavigatorDisplayMode getDisplayModeForCategory(String name) {
        return this.getDisplayModeForCategory(name, NavigatorDisplayMode.LIST);
    }

    @Override
    public NavigatorDisplayMode getDisplayModeForCategory(String name, NavigatorDisplayMode defaultMode) {
        final IHabboNavigatorCategorySetting categorySettings = this.getNavigatorCategorySettingsFromName(name);

        if(categorySettings != null) {
            return categorySettings.getDisplayMode();
        }

        return defaultMode;
    }

    @Override
    public NavigatorLayoutDisplay getLayoutDisplayForCategory(String name) {
        return this.getLayoutDisplayForCategory(name, NavigatorLayoutDisplay.DEFAULT);
    }

    @Override
    public NavigatorLayoutDisplay getLayoutDisplayForCategory(String name, NavigatorLayoutDisplay defaultLayout) {
        final IHabboNavigatorCategorySetting categorySettings = this.getNavigatorCategorySettingsFromName(name);

        if(categorySettings != null) {
            return categorySettings.getLayoutDisplay();
        }

        return defaultLayout;
    }

    @Override
    public THashSet<IHabboNavigatorSearch> getNavigatorSearchForTab(String tabName) {
        final THashSet<IHabboNavigatorSearch> searches = new THashSet<>();

        for (final IHabboNavigatorSearch search : this.navigatorSearches) {
            if(search.getSearchCode().equals(tabName)) {
                searches.add(search);
            }
        }

        return searches;
    }
}
