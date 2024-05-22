package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboInventoryRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboInventoryQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class HabboInventoryRepository extends DatabaseRepository implements IHabboInventoryRepository {
    protected Logger logger = LogManager.getLogger();

    @Override
    public void loadAllHabboItems(IConnectionResultConsumer consumer, int habboId) {
        this.select(HabboInventoryQuery.LOAD_ALL_HABBO_ITEMS.get(), consumer, habboId);
    }
}
