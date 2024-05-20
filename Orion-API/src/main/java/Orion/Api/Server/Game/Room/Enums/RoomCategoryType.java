package Orion.Api.Server.Game.Room.Enums;

public enum RoomCategoryType {
    BUILDERS_CLUB("${navigator.flatcategory.global.BC}"),
    BUILD_AND_DECORATION("${navigator.flatcategory.global.BUILDING}"),
    CHAT_AND_DISCUSSION("${navigator.flatcategory.global.CHAT}"),
    FANSITE_SQUARE("${navigator.flatcategory.global.FANSITE}"),
    HABBO_GAMES("${navigator.flatcategory.global.GAMES}"),
    HELP_CENTERS("${navigator.flatcategory.global.HELP}"),
    HABBO_LIFE("${navigator.flatcategory.global.LIFE}"),
    PUBLIC_ROOMS("${navigator.flatcategory.global.OFFICIAL}"),
    PARTY("${navigator.flatcategory.global.PARTY}")

    ;

    private final String category;

    RoomCategoryType(String category) {
        this.category = category;
    }

    public static RoomCategoryType fromCategoryId(int categoryId) {
        for (RoomCategoryType category : RoomCategoryType.values()) {
            if (category.getId() == categoryId) {
                return category;
            }
        }

        return null;
    }

    public String get() {
        return this.category;
    }

    // Room categories starts from 1
    public int getId() {
        return this.ordinal() + 1;
    }
}
