package Orion.Api.Server.Game.Room;

import Orion.Api.Networking.Message.IComposer;
import Orion.Api.Networking.Message.IMessageComposer;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.Component.*;
import Orion.Api.Server.Game.Room.Data.IRoomData;
import Orion.Api.Server.Game.Room.Data.Model.IRoomModel;
import Orion.Api.Server.Game.Room.Object.Entity.IRoomEntity;
import Orion.Api.Server.Game.Room.Process.IRoomProcess;
import Orion.Api.Util.IDisposable;
import Orion.Api.Util.IWriteable;
import Orion.Api.Util.Initializable;

public interface IRoom extends Comparable<IRoom>, IWriteable, Initializable, IDisposable {
    IRoomData getData();

    IRoomModel getModel();

    IRoomProcess getProcess();

    boolean isFullyLoaded();

    void setFullyLoaded(boolean isFullyLoaded);

    IRoomMappingComponent getMappingComponent();

    IRoomEntitiesComponent getEntitiesComponent();

    IRoomRightsComponent getRightsComponent();

    IRoomBansComponent getBansComponent();

    IRoomVotesComponent getVotesComponent();

    IRoomItemsComponent getItemsComponent();

    void onEntityRemoved(final IRoomEntity entity);

    void broadcastMessage(final IComposer composer);

    void broadcastMessages(IComposer... composers);

    boolean habboIsOwner(final IHabbo habbo);

    boolean isMuted();
}
