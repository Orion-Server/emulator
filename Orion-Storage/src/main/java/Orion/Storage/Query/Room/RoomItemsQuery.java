package Orion.Storage.Query.Room;

public enum RoomItemsQuery {
    LOAD_ALL_ITEM_DEFINITIONS("SELECT * FROM items_base"),

    LOAD_ITEMS_BY_ROOM_ID("SELECT items.*, users.username as owner_name FROM items JOIN users ON items.user_id = users.id WHERE room_id = ?");

    private final String query;

    RoomItemsQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
