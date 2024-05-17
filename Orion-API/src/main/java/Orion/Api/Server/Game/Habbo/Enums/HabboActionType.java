package Orion.Api.Server.Game.Habbo.Enums;

public enum HabboActionType {
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

    HabboActionType(int value) {
        this.value = value;
    }

    public static HabboActionType fromValue(int value) {
        for (final HabboActionType type : values()) {
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
