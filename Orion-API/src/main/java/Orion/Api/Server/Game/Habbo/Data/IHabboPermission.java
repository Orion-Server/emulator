package Orion.Api.Server.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Room.IRoom;

public interface IHabboPermission {
    void setHabbo(IHabbo habbo);boolean hasCommandPermissions(String[] permissions);

    boolean hasCommandPermissions(String[] permissions, IRoom room);

    boolean hasCommandPermission(String permission);

    boolean hasCommandPermission(String permission, IRoom room);

    boolean hasAccountPermissions(String[] permissions);

    boolean hasAccountPermissions(String[] permissions, IRoom room);

    boolean hasAccountPermission(String permission);

    boolean hasAccountPermission(String permission, IRoom room);
}
