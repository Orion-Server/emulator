package Orion.Api.Storage.Repository.HotelView;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHotelViewRepository {
    void loadHallOfFameWinners(IConnectionResultConsumer consumer);

    void loadHotelViewArticles(IConnectionResultConsumer consumer);
}
