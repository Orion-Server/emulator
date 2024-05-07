package Orion.Storage.Repository.Navigator;

import Orion.Api.Storage.Repository.Navigator.INavigatorRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Navigator.NavigatorQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class NavigatorRepository extends DatabaseRepository implements INavigatorRepository {
    public void loadPublicCategories(IConnectionResultConsumer consumer) {
        this.select(NavigatorQuery.SELECT_ALL_PUBLIC_CATEGORIES.get(), consumer);
    }

    public void loadFilterTypes(IConnectionResultConsumer consumer) {
        this.select(NavigatorQuery.SELECT_ALL_FILTER_TYPES.get(), consumer);
    }
}

