package Orion.Api.Server.Game.Room.Object.Entity.Enum;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;

public enum RoomEntityType {
    HABBO,
    BOT,
    PET;

    public static RoomEntityType fromEntityInstance(final IRoomEntity entity) {
        if (entity instanceof IHabboEntity) {
            return RoomEntityType.HABBO;
        }

        return null;
    }
}
