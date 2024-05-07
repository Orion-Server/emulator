package Orion.Storage.Repository.HotelView;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import Orion.Api.Storage.Repository.HotelView.IHotelViewRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.HotelView.HotelViewQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class HotelViewRepository extends DatabaseRepository implements IHotelViewRepository {
    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    public void loadHallOfFameWinners(IConnectionResultConsumer consumer) {
        this.select(this.databaseSettings.getSetting("hotelview.halloffame.query"), consumer);
    }

    public void loadHotelViewArticles(IConnectionResultConsumer consumer) {
        this.select(HotelViewQuery.SELECT_HOTEL_VIEW_ARTICLES.get(), consumer);
    }
}
