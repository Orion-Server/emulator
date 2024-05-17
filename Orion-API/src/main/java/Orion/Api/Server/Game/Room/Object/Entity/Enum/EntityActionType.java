package Orion.Api.Server.Game.Room.Object.Entity.Enum;

public enum EntityActionType {
    NONE(0),
    WAVE(1),
    BLOW_KISS(2),
    LAUGH(3),
    CRY(4),
    IDLE(5),
    JUMP(6),
    THUMB_UP(7)

    ;

    private final int value;

    EntityActionType(int value) {
        this.value = value;
    }

    public static EntityActionType fromValue(int value) {
        for (final EntityActionType type : values()) {
            if (type.get() == value) {
                return type;
            }
        }

        return NONE;
    }

    public int get() {
        return value;
    }
}
