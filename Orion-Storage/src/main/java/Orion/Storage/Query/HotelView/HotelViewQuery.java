package Orion.Storage.Query.HotelView;

public enum HotelViewQuery {
    SELECT_HOTEL_VIEW_ARTICLES("SELECT * FROM hotelview_news ORDER BY id DESC LIMIT 10"),

    ;

    private final String query;

    HotelViewQuery(String query) {
        this.query = query;
    }

    public String get() {
        return this.query;
    }
}
