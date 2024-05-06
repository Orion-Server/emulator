package Orion.Game.Habbo.Data;

import Orion.Api.Server.Game.Habbo.Data.IHabboPermission;
import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Permission.IPermissionManager;
import Orion.Api.Server.Game.Room.IRoom;
import com.google.inject.Inject;

public class HabboPermission implements IHabboPermission {
    @Inject
    private IPermissionManager permissionManager;

    private IHabbo habbo;

    @Override
    public void setHabbo(IHabbo habbo) {
        this.habbo = habbo;
    }

    @Override
    public boolean hasCommandPermissions(String[] permissions) {
        return this.hasCommandPermissions(permissions, null);
    }

    @Override
    public boolean hasCommandPermissions(String[] permissions, IRoom room) {
        return this.permissionManager.habboHasCommandPermissions(this.habbo, permissions, room);
    }

    @Override
    public boolean hasCommandPermission(String permission) {
        return this.hasCommandPermission(permission, null);
    }

    @Override
    public boolean hasCommandPermission(String permission, IRoom room) {
        return this.permissionManager.habboHasCommandPermission(this.habbo, permission, room);
    }

    @Override
    public boolean hasAccountPermissions(String[] permissions) {
        return this.hasAccountPermissions(permissions, null);
    }

    @Override
    public boolean hasAccountPermissions(String[] permissions, IRoom room) {
        return this.permissionManager.habboHasAccountPermissions(this.habbo, permissions, room);
    }

    @Override
    public boolean hasAccountPermission(String permission) {
        return this.hasAccountPermission(permission, null);
    }

    @Override
    public boolean hasAccountPermission(String permission, IRoom room) {
        return this.permissionManager.habboHasAccountPermission(this.habbo, permission, room);
    }
}
