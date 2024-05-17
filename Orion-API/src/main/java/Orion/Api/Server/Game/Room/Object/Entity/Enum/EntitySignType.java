package Orion.Api.Server.Game.Room.Object.Entity.Enum;

public enum EntitySignType {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    HEART(11),
    SKULL(12),
    BALL(14),
    YELLOW_CARD(17),
    RED_CARD(16)

    ;

    private final int value;

    EntitySignType(int value) {
        this.value = value;
    }

    public static EntitySignType fromValue(int value) {
        for (final EntitySignType type : values()) {
            if (type.get() == value) {
                return type;
            }
        }

        return ONE;
    }

    public int get() {
        return value;
    }
}
