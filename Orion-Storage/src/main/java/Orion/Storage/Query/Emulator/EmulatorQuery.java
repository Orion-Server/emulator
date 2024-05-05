package Orion.Storage.Query.Emulator;

public enum EmulatorQuery {
    LOAD_ALL_SETTINGS("SELECT * FROM emulator_settings")

    ;

    private final String query;

    EmulatorQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
