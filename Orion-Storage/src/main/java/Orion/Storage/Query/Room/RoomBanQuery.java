package Orion.Storage.Query.Room;

public enum RoomBanQuery {
    SELECT_ALL_ROOM_BANS("SELECT users.username, users.id, room_bans.* FROM room_bans INNER JOIN users ON room_bans.user_id = users.id WHERE ends > ? AND room_bans.room_id = ?;"),

    ;

    private final String query;

    RoomBanQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
