package Orion.Api.Server.Game.Room.Object.Entity.Enum;

public enum RoomEntityStatus {
    FLATCTRL("flatctrl"),
    MOVE("mv"),
    GESTURE("gst"),
    SIT("sit"),
    LAY("lay")

    ;

    private final String status;

    RoomEntityStatus(final String status) {
        this.status = status;
    }

    public String get() {
        return this.status;
    }
}
