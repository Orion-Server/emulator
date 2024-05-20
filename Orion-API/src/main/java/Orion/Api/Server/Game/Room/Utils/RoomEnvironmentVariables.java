package Orion.Api.Server.Game.Room.Utils;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class RoomEnvironmentVariables {
    @Inject
    private IEmulatorDatabaseSettings databaseSettings;

    public int userRoomsLimit;
    public int hcUserRoomsLimit;

    public int limitUsersPerRoom = 250;
    public int minRoomNameLength = 3;
    public int maxRoomNameLength = 25;
    public int minRoomDescriptionLength = 3;
    public int maxRoomDescriptionLength = 128;

    public void initialize() {
        this.userRoomsLimit = this.databaseSettings.getIntegerOrDefault("hotel.users.max.rooms", 100);
        this.hcUserRoomsLimit = this.databaseSettings.getIntegerOrDefault("hotel.users.max.rooms.hc", 200);
    }
}
