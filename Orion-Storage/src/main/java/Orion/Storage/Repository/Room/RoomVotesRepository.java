package Orion.Storage.Repository.Room;

import Orion.Api.Storage.Repository.Room.IRoomVotesRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Room.RoomVoteQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class RoomVotesRepository extends DatabaseRepository implements IRoomVotesRepository {
    @Override
    public void loadAllVotes(IConnectionResultConsumer consumer, int roomId) {
        this.select(RoomVoteQuery.SELECT_ALL_ROOM_VOTES.get(), consumer, roomId);
    }
}
