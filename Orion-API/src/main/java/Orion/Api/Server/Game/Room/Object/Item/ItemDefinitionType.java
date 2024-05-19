package Orion.Api.Server.Game.Room.Object.Item;

public enum ItemDefinitionType {
    FLOOR("s"),
    WALL("i"),
    EFFECT("e"),
    BADGE("b"),
    ROBOT("r"),
    PET("p"),
    HABBO_CLUB("h")

    ;

    private final String type;

    ItemDefinitionType(String type) {
        this.type = type;
    }

    public static ItemDefinitionType fromResult(String type) {
        for (final ItemDefinitionType itemDefinitionType : ItemDefinitionType.values()) {
            if (itemDefinitionType.get().equals(type)) {
                return itemDefinitionType;
            }
        }

        return null;
    }

    public String get() {
        return type;
    }
}
