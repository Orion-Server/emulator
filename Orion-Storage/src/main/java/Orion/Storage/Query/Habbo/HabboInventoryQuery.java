package Orion.Storage.Query.Habbo;

public enum HabboInventoryQuery {
    LOAD_ALL_HABBO_ITEMS("SELECT * FROM `items` WHERE `user_id` = ? AND room_id = 0"),
    LOAD_ALL_HABBO_BOTS("SELECT users.username AS owner_name, bots.* FROM bots INNER JOIN users ON users.id = bots.user_id WHERE user_id = ? AND room_id = 0 ORDER BY id ASC"),

    ;

    private final String query;

    HabboInventoryQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
