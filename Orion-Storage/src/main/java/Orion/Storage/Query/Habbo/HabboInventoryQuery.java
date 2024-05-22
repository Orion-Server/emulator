package Orion.Storage.Query.Habbo;

public enum HabboInventoryQuery {
    LOAD_ALL_HABBO_INVENTORY("SELECT * FROM `items` WHERE `user_id` = ? AND room_id = 0"),

    ;

    private final String query;

    HabboInventoryQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
