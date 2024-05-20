package Orion.Storage.Query.Room;

public enum RoomQuery {
    SELECT_ALL_PUBLIC_ROOMS("SELECT * FROM rooms WHERE is_public = ? OR is_staff_picked = ? ORDER BY id DESC"),

    SELECT_ALL_STAFF_PICKED_ROOMS("SELECT * FROM navigator_publics JOIN rooms ON rooms.id = navigator_publics.room_id WHERE visible = ?"),

    SELECT_ALL_ROOMS_CATEGORIES("SELECT * FROM navigator_flatcats"),

    SELECT_ALL_ROOM_MODELS("SELECT * FROM room_models"),

    LOAD_CUSTOM_ROOM_MODEL("SELECT * FROM room_models_custom WHERE id = ?"),

    CREATE_ROOM("INSERT INTO rooms (owner_id, owner_name, name, description, model, users_max, category, trade_mode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"),

    SELECT_ROOM_BY_ID("SELECT * FROM rooms WHERE id = ? LIMIT 1"),

    ;

    private final String query;

    RoomQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
