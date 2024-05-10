package Orion.Api.Server.Game.Util.Alert;

public enum GenericErrorType {
    AUTH_FAILED(-3),
    CONNECT_TO_SERVER_FAILED(-400),
    ROOM_KICK(4008),
    NEED_TO_VIP(4009),
    ROOM_NAME_DENIED(4010),
    BAN_GROUP_MEMBER_DENIED(4011),
    WRONG_PASSWORD(-100002),

    ;

    private final int errorCode;

    GenericErrorType(int errorCode) {
        this.errorCode = errorCode;
    }

    public int get() {
        return errorCode;
    }
}
