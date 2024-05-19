package Orion.Module;

import Orion.Api.Server.Game.Room.Handler.IJoinRoomHandler;
import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Room.Object.Item.IRoomItemManager;
import Orion.Api.Server.Game.Room.Object.Pathfinder.IPathfinder;
import Orion.Api.Server.Game.Room.Utils.RoomEnvironmentVariables;
import Orion.Api.Storage.Repository.Room.*;
import Orion.Game.Room.Factory.RoomFactory;
import Orion.Game.Room.Factory.RoomModelFactory;
import Orion.Game.Room.Handler.JoinRoomHandler;
import Orion.Game.Room.Object.Entity.Factory.HabboEntityFactory;
import Orion.Game.Room.Object.Item.Factory.RoomItemFactory;
import Orion.Game.Room.Object.Item.RoomItemManager;
import Orion.Game.Room.Object.Pathfinder.Pathfinder;
import Orion.Game.Room.RoomManager;
import Orion.Storage.Repository.Room.RoomItemsRepository;
import Orion.Storage.Repository.Room.RoomBansRepository;
import Orion.Storage.Repository.Room.RoomRepository;
import Orion.Storage.Repository.Room.RoomRightsRepository;
import Orion.Storage.Repository.Room.RoomVotesRepository;
import com.google.inject.AbstractModule;

public class RoomModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRoomManager.class).to(RoomManager.class);
        bind(IRoomItemManager.class).to(RoomItemManager.class);
        bind(RoomEnvironmentVariables.class).asEagerSingleton();
        bind(RoomItemFactory.class).asEagerSingleton();

        bind(RoomFactory.class).asEagerSingleton();
        bind(RoomModelFactory.class).asEagerSingleton();
        bind(HabboEntityFactory.class).asEagerSingleton();

        bind(IRoomRepository.class).to(RoomRepository.class);
        bind(IRoomRightsRepository.class).to(RoomRightsRepository.class);
        bind(IRoomBansRepository.class).to(RoomBansRepository.class);
        bind(IRoomVotesRepository.class).to(RoomVotesRepository.class);
        bind(IRoomItemsRepository.class).to(RoomItemsRepository.class);

        bind(IJoinRoomHandler.class).to(JoinRoomHandler.class);

        bind(IPathfinder.class).to(Pathfinder.class);
    }
}
