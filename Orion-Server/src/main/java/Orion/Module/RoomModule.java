package Orion.Module;

import Orion.Api.Server.Game.Room.IRoomManager;
import Orion.Game.Room.Factory.RoomFactory;
import Orion.Game.Room.RoomManager;
import com.google.inject.AbstractModule;

public class RoomModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IRoomManager.class).to(RoomManager.class);
        bind(RoomFactory.class).asEagerSingleton();
    }
}
