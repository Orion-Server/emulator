package Orion.Api.Storage.Repository.Habbo;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IHabboRepository {
    void getHabboIdByAuthTicket(final IConnectionResultConsumer consumer, String authTicket);

    void getHabboDataByAuthTicket(final IConnectionResultConsumer consumer, String authTicket);
}
