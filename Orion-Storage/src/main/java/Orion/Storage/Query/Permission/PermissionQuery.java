package Orion.Storage.Query.Permission;

public enum PermissionQuery {
    SELECT_ALL_PERMISSIONS("SELECT * FROM permissions"),

    ;

    private final String query;

    PermissionQuery(String query) {
        this.query = query;
    }

    public String get() {
        return query;
    }
}
