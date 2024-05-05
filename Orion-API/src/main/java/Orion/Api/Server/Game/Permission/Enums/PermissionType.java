package Orion.Api.Server.Game.Permission.Enums;

public enum PermissionType {
    DENIED(0),
    ALLOWED(1),
    RIGHTS(2);

    private final int value;

    PermissionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PermissionType fromValue(int value) {
        return switch (value) {
            case 1 -> ALLOWED;
            case 2 -> RIGHTS;
            default -> DENIED;
        };
    }

    public static PermissionType fromValue(String value) {
        return PermissionType.fromValue(Integer.parseInt(value));
    }
}
