package Orion.Api.Server.Game.Room.Enums;

public enum RoomTradeType {
    NOT_ALLOWED(0),
    USER_WITH_RIGHTS(1),
    EVERYONE(2)

    ;

    private final int value;

    RoomTradeType(int value) {
        this.value = value;
    }

    public static RoomTradeType fromValue(int value) {
        for (RoomTradeType type : values()) {
            if (type.get() == value) {
                return type;
            }
        }

        return null;
    }

    public int get() {
        return value;
    }
}
