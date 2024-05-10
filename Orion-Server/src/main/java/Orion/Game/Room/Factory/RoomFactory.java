package Orion.Game.Room.Factory;

import Orion.Api.Server.Game.Room.Data.Ban.IRoomBan;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Util.TimeUtil;
import Orion.Api.Storage.Repository.Room.IRoomBansRepository;
import Orion.Api.Storage.Repository.Room.IRoomRightsRepository;
import Orion.Api.Storage.Repository.Room.IRoomVotesRepository;
import Orion.Api.Storage.Result.IConnectionResult;
import Orion.Game.Room.Data.Bans.RoomBan;
import Orion.Game.Room.Room;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class RoomFactory {
    private final Logger logger = LogManager.getLogger();

    @Inject
    private IRoomManager roomManager;

    @Inject
    private IRoomRightsRepository rightsRepository;

    @Inject
    private IRoomBansRepository bansRepository;

    @Inject
    private IRoomVotesRepository votesRepository;

    public IRoom create(IConnectionResult data) {
        try {
            final IRoomModel model = roomManager.getRoomModelByName(data.getString("model"));

            if(model == null) {
                throw new UnsupportedOperationException("Room model not found");
            }

            final IRoom room = new Room(data, model);

            room.getRightsComponent().setUsersWithRights(this.loadUsersWithRights(room.getData().getId()));
            room.getBansComponent().setBans(this.loadValidRoomBans(room.getData().getId()));

            this.roomManager.addRoom(room);

            return room;
        } catch (Exception e) {
            logger.error("Error while creating room", e);
            return null;
        }
    }

    private List<Integer> loadUsersWithRights(int roomId) {
        final List<Integer> usersWithRights = new ArrayList<>();

        this.rightsRepository.loadHabboWithRights(result -> {
            if(result == null) return;

            usersWithRights.add(result.getInt("user_id"));
        }, roomId);

        return usersWithRights;
    }

    private List<IRoomBan> loadValidRoomBans(int roomId) {
        final List<IRoomBan> bans = new ArrayList<>();

        this.bansRepository.loadAllValidBans(result -> {
            if(result == null) return;

            bans.add(new RoomBan(result));
        }, roomId, TimeUtil.now());

        return bans;
    }
}
