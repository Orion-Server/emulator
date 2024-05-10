package Orion.Storage.Query.Room;

public enum RoomVoteQuery {
    SELECT_ALL_ROOM_VOTES("SELECT * FROM room_votes WHERE room_id = ?"),

    INSERT_ROOM_VOTE("INSERT INTO room_votes (room_id, user_id) VALUES (?, ?)"),

    DELETE_ROOM_VOTE("DELETE FROM room_votes WHERE room_id = ? AND user_id = ?")

    ;

    private final String query;

    RoomVoteQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
