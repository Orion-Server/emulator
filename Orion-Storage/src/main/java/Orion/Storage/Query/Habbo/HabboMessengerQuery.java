package Orion.Storage.Query.Habbo;

public enum HabboMessengerQuery {
    LOAD_ALL_MESSENGER_CATEGORIES("SELECT * FROM messenger_categories WHERE user_id = ?"),

    LOAD_ALL_MESSENGER_FRIENDS("""
            SELECT users.id, users.username, users.gender, users.online, users.look, users.motto, messenger_friendships.*
            FROM messenger_friendships INNER JOIN users ON messenger_friendships.user_two_id = users.id WHERE user_one_id = ?
    """),

    LOAD_ALL_MESSENGER_FRIEND_REQUESTS(
            "SELECT users.id, users.username, users.look FROM messenger_friendrequests INNER JOIN users ON user_from_id = users.id WHERE user_to_id = ?"
    ),

    ;

    private final String query;

    HabboMessengerQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
