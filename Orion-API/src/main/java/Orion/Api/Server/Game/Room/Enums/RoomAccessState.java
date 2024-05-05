package Orion.Api.Server.Game.Room.Enums;

public enum RoomAccessState {
    OPEN(0),
    LOCKED(1),
    PASSWORD(2),
    INVISIBLE(3),
    NOOB_STATE(4);

    private final int state;

    RoomAccessState(int state) {
        this.state = state;
    }

    public static RoomAccessState fromValue(String state) {
        return switch (state) {
            case "locked" -> LOCKED;
            case "password" -> PASSWORD;
            case "invisible" -> INVISIBLE;
            default -> OPEN;
        };
    }

    public int getState() {
        return state;
    }
}
