package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.Initializable;

import java.util.List;

public interface IRoomEntitiesComponent extends IDisposable, Initializable {
    int getCurrentVirtualId();

    int getNextVirtualId();

    void addEntity(IRoomEntity entity);;

    IHabboEntity createHabboEntity(IHabbo habbo);

    void removeEntity(IRoomEntity entity);

    List<IHabboEntity> getHabboEntities();

    List<IRoomEntity> getRoomEntities();

    List<IHabboEntity> getHabbosWithRights();
}
