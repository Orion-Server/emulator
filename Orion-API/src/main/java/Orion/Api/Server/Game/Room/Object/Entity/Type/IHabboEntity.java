package Orion.Api.Server.Game.Room.Object.Entity.Type;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Object.Entity.Component.IEntityWalkComponent;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;

public interface IHabboEntity extends IRoomEntity {
    IHabbo getHabbo();

    void setHabbo(IHabbo habbo);

    IEntityWalkComponent getWalkComponent();

    boolean isWalking();

    boolean isDisposed();

    void lookAt(final int x, final int y);
}
