package Orion.Storage.Query.Room;

public enum RoomItemsQuery {
    LOAD_ALL_ITEM_DEFINITIONS("SELECT * FROM items_base"),

    LOAD_ITEMS_BY_ROOM_ID("SELECT * FROM items WHERE room_id = ?");

    private final String query;

    RoomItemsQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
