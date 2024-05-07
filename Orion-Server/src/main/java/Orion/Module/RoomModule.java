package Orion.Module;

import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Api.Server.Game.Room.Utils.RoomEnvironmentVariables;
import Orion.Api.Storage.Repository.Room.IRoomRepository;
import Orion.Game.Room.Factory.RoomFactory;
import Orion.Game.Room.RoomManager;
import Orion.Storage.Repository.Room.RoomRepository;
import com.google.inject.AbstractModule;

public class RoomModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRoomManager.class).to(RoomManager.class);
        bind(RoomFactory.class).asEagerSingleton();

        bind(IRoomRepository.class).to(RoomRepository.class);
        bind(RoomEnvironmentVariables.class).asEagerSingleton();
    }
}
