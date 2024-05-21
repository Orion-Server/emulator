package Orion.Api.Server.Game.Room.Component;

import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Object.Entity.Type.IHabboEntity;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.Initializable;

import java.util.Collection;
import java.util.List;

public interface IRoomEntitiesComponent extends IDisposable, Initializable {
    int getCurrentVirtualId();

    int getNextVirtualId();

    void addEntity(IRoomEntity entity);;

    void removeEntity(IRoomEntity entity);

    Collection<IHabboEntity> getHabboEntities();

    int getHabboEntitiesCount();

    Collection<IRoomEntity> getRoomEntities();

    List<IHabboEntity> getHabbosWithRights();
}
