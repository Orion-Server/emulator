package Orion.Api.Storage.Repository.Room;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IRoomVotesRepository {
    void loadAllVotes(IConnectionResultConsumer consumer, int roomId);
}
