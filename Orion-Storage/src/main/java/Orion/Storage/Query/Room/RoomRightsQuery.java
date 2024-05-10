package Orion.Storage.Query.Room;

public enum RoomRightsQuery {
    LOAD_HABBO_WITH_RIGHTS("SELECT user_id FROM room_rights WHERE room_id = ?"),

    ;

    private final String query;

    RoomRightsQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
