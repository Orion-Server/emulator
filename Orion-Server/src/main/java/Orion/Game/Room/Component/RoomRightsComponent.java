package Orion.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Component.IRoomRightsComponent;
import Orion.Api.Server.Game.Room.Enums.RoomRightLevel;
import Orion.Api.Server.Game.Room.IRoom;

import java.util.ArrayList;
import java.util.List;

public class RoomRightsComponent implements IRoomRightsComponent {
    private final IRoom room;

    private final List<Integer> usersWithRights;

    public RoomRightsComponent(final IRoom room) {
        this.room = room;
        this.usersWithRights = new ArrayList<>();
    }

    @Override
    public void setUsersWithRights(final List<Integer> usersWithRights) {
        this.usersWithRights.clear();
        this.usersWithRights.addAll(usersWithRights);
    }

    @Override
    public boolean hasRights(IHabbo habbo) {
        return this.room.habboIsOwner(habbo) || this.usersWithRights.contains(habbo.getData().getId());
    }

    @Override
    public boolean hasRights(IHabbo habbo, RoomRightLevel level) {
        return this.usersWithRights.contains(habbo.getData().getId());
    }

    @Override
    public RoomRightLevel getRightLevelFor(IHabbo habbo) {
        // TODO: Implement
        return RoomRightLevel.Moderator;
    }
}
