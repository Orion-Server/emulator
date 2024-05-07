package Orion.Api.Server.Game.Room.Utils;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RoomEnvironmentVariables {
    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    public int userRoomsLimit = 75;
    public int hcUserRoomsLimit = 100;

    public void initialize() {
        this.userRoomsLimit = this.databaseSettings.getIntegerOrDefault("hotel.users.max.rooms", 100);
        this.hcUserRoomsLimit = this.databaseSettings.getIntegerOrDefault("hotel.users.max.rooms.hc", 200);
    }
}
