package Orion.Storage.Repository.Habbo;

import Orion.Api.Storage.Repository.Habbo.IHabboRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Habbo.HabboQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class HabboRepository extends DatabaseRepository implements IHabboRepository {
    protected Logger logger = LogManager.getLogger();

    public void getHabboIdByAuthTicket(final IConnectionResultConsumer consumer, String authTicket) {
        this.select(HabboQuery.GET_ID_BY_AUTH_TICKET.get(), consumer, authTicket);
    }

    public void getHabboDataByAuthTicket(final IConnectionResultConsumer consumer, String authTicket) {
        this.select(HabboQuery.GET_ALL_DATA_BY_AUTH_TICKET.get(), consumer, authTicket);
    }
}
