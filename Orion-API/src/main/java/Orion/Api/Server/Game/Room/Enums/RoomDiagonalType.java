package Orion.Api.Server.Game.Room.Enums;

public enum RoomDiagonalType {
    DISABLED(0),
    OFFICIAL(1),
    NO_RULES(2)

    ;

    private final int value;

    RoomDiagonalType(int value) {
        this.value = value;
    }

    public static boolean isAllowed(final RoomDiagonalType type) {
        return type != DISABLED;
    }

    public int get() {
        return value;
    }
}
