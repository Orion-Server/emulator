package Orion.Api.Server.Game.Permission;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Permission.Data.IPermission;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Util.Initializable;

public interface IPermissionManager extends Initializable {
    IPermission getPermissionById(int id);

    boolean habboHasCommandPermissions(IHabbo habbo, String[] commands);

    boolean habboHasCommandPermissions(IHabbo habbo, String[] commands, IRoom room);

    boolean habboHasCommandPermission(IHabbo habbo, String command);

    boolean habboHasCommandPermission(IHabbo habbo, String command, IRoom room);

    boolean habboHasAccountPermissions(IHabbo habbo, String[] permissions);

    boolean habboHasAccountPermissions(IHabbo habbo, String[] permissions, IRoom room);

    boolean habboHasAccountPermission(IHabbo habbo, String permission);

    boolean habboHasAccountPermission(IHabbo habbo, String command, IRoom room);
}
