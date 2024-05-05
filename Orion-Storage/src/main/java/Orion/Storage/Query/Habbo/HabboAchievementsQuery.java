package Orion.Storage.Query.Habbo;

public enum HabboAchievementsQuery {
    LOAD_ALL_ACHIEVEMENTS("SELECT * FROM users_achievements WHERE user_id = ?"),

    ;

    private final String query;

    HabboAchievementsQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
