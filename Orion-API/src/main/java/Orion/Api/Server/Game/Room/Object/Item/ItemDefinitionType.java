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

    public static boolean definitionTypeIsInvalid(String type) {
        for (ItemDefinitionType itemDefinitionType : ItemDefinitionType.values()) {
            if (itemDefinitionType.get().equals(type)) {
                return false;
            }
        }

        return true;
    }

    public String get() {
        return type;
    }
}
