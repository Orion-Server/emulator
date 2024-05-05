package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboNavigatorRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboNavigatorQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class HabboNavigatorRepository extends DatabaseRepository implements IHabboNavigatorRepository {
    @Override
    public void loadNavigatorSearches(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboNavigatorQuery.SELECT_ALL_NAVIGATOR_SEARCHES.get(), consumer, habboId);
    }

    @Override
    public void loadNavigatorCategoriesSettings(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboNavigatorQuery.SELECT_ALL_NAVIGATOR_CATEGORIES_SETTINGS.get(), consumer, habboId);
    }
}
