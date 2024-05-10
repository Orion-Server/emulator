package Orion.Api.Server.Game.Room.Enums;

public enum RoomAccessError {
    ROOM_FULL(1),
    CANT_ENTER(2),
    QUEUE_ERROR(3),
    ROOM_BANNED(4),

    ;

    private final int code;

    RoomAccessError(int code) {
        this.code = code;
    }

    public int get() {
        return code;
    }
}
