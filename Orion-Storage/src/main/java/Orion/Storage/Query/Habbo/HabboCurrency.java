package Orion.Storage.Query.Habbo;

public enum HabboCurrency {
    SELECT_ALL_HABBO_CURRENCIES("SELECT * FROM users_currency WHERE user_id = ?"),

    ;

    private final String query;

    HabboCurrency(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
