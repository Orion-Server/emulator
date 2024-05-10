package Orion.Api.Server.Game.Room.Enums;

public enum RoomQueueAccessError {
    NEEDS_VIP("c"),
    EVENT_USERS_ONLY("e1"),
    ROOM_LOCKED("na"),
    TO_MANY_SPECTATORS("spectator_mode_full"),

    ;

    private final String message;

    RoomQueueAccessError(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
