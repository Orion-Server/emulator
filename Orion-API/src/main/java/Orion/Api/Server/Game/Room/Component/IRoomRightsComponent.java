package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Enums.RoomRightLevel;

import java.util.List;

public interface IRoomRightsComponent {
    void setUsersWithRights(final List<Integer> usersWithRights);

    boolean hasRights(IHabbo habbo);

    boolean hasRights(IHabbo habbo, RoomRightLevel level);

    RoomRightLevel getRightLevelFor(IHabbo habbo);
}
