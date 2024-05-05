package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboNavigatorRepository {
    void loadNavigatorSearches(IConnectionResultConsumer consumer, int habboId);

    void loadNavigatorCategoriesSettings(IConnectionResultConsumer consumer, int habboId);
}
