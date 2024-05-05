package Orion.Storage.Query.Achievement;

public enum AchievementQuery {
    LOAD_ALL_ACHIEVEMENTS("SELECT * FROM achievements"),

    ;

    private final String query;

    AchievementQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
