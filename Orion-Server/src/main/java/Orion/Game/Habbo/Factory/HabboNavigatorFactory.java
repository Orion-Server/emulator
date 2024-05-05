package Orion.Game.Habbo.Factory;

import Orion.Api.Server.Game.Habbo.Data.IHabboNavigator;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorCategorySetting;
import Orion.Api.Server.Game.Habbo.Data.Navigator.IHabboNavigatorSearch;
import Orion.Api.Storage.Repository.Habbo.IHabboNavigatorRepository;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Habbo.Data.HabboNavigator;
import Orion.Game.Habbo.Data.Navigator.HabboNavigatorCategorySetting;
import Orion.Game.Habbo.Data.Navigator.HabboNavigatorSearch;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

@Singleton
public class HabboNavigatorFactory {
    @Inject
    private IHabboNavigatorRepository repository;

    public IHabboNavigator create(final IConnectionResult data) {
        try {
            final int habboId = data.getInt("id");

            final IHabboNavigator navigator = new HabboNavigator(data);

            navigator.setNavigatorCategoriesSettings(this.loadNavigatorCategoriesSettings(habboId));
            navigator.setNavigatorSearches(this.loadNavigatorSearches(habboId));

            return navigator;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private THashSet<IHabboNavigatorSearch> loadNavigatorSearches(final int habboId) {
        final THashSet<IHabboNavigatorSearch> navigatorSearches = new THashSet<>();

        this.repository.loadNavigatorSearches(result -> {
            if(result == null) return;

            navigatorSearches.add(new HabboNavigatorSearch(result));
        }, habboId);

        return navigatorSearches;
    }

    private THashMap<String, IHabboNavigatorCategorySetting> loadNavigatorCategoriesSettings(final int habboId) {
        final THashMap<String, IHabboNavigatorCategorySetting> navigatorCategoriesSettings = new THashMap<>();

        this.repository.loadNavigatorCategoriesSettings(result -> {
            if(result == null) return;

            final IHabboNavigatorCategorySetting categorySettings = new HabboNavigatorCategorySetting(result);

            navigatorCategoriesSettings.put(categorySettings.getCaption(), categorySettings);
        }, habboId);

        return navigatorCategoriesSettings;
    }
}
