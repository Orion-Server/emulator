package Orion.Api.Storage.Repository.Navigator;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface INavigatorRepository {
    void loadPublicCategories(IConnectionResultConsumer consumer);

    void loadFilterTypes(IConnectionResultConsumer consumer);
}
