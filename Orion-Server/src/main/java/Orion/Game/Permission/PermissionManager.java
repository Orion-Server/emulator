package Orion.Game.Permission;

import Orion.Api.Server.Game.Habbo.IHabbo;
import Orion.Api.Server.Game.Permission.Data.IPermission;
import Orion.Api.Server.Game.Permission.Enums.PermissionType;
import Orion.Api.Server.Game.Permission.IPermissionManager;
import Orion.Api.Server.Game.Room.IRoom;
import Orion.Api.Storage.Repository.Permission.IPermissionRepository;
import Orion.Game.Permission.Data.Permission;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class PermissionManager implements IPermissionManager {
    private final Logger logger = LogManager.getLogger();

    private final THashMap<Integer, IPermission> permissions;

    @Inject
    private IPermissionRepository repository;

    public PermissionManager() {
        this.permissions = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.loadPermissionsFromDatabase();
    }

    @Override
    public IPermission getPermissionById(int id) {
        return this.permissions.get(id);
    }

    @Override
    public boolean habboHasCommandPermissions(IHabbo habbo, String[] permissions) {
        return this.habboHasCommandPermissions(habbo, permissions, null);
    }

    @Override
    public boolean habboHasCommandPermissions(IHabbo habbo, String[] permissions, IRoom room) {
        final IPermission habboPermission = this.getPermissionById(habbo.getData().getRank());

        if(habboPermission == null) return false;

        boolean hasPermission = true;

        for(final String cmdName : permissions) {
            if(!hasPermission) break;

            final PermissionType permissionType = habboPermission.getCommandPermission(cmdName);

            if (permissionType != PermissionType.ALLOWED) {
                hasPermission = false;
            }

            if(permissionType == PermissionType.RIGHTS) {
                // TODO: Check room rights
                hasPermission = room != null && room.getData().getOwnerId() == habbo.getData().getId();
            }
        }

        return hasPermission;
    }

    @Override
    public boolean habboHasCommandPermission(IHabbo habbo, String permission) {
        return this.habboHasCommandPermission(habbo, permission, null);
    }

    @Override
    public boolean habboHasCommandPermission(IHabbo habbo, String permission, IRoom room) {
        final IPermission habboPermission = this.getPermissionById(habbo.getData().getRank());

        if(habboPermission == null) return false;

        final PermissionType permissionType = habboPermission.getCommandPermission(permission);

        if(permissionType == PermissionType.ALLOWED) return true;

        if(permissionType == PermissionType.RIGHTS) {
            // TODO: Check room rights
            return room != null && room.getData().getOwnerId() == habbo.getData().getId();
        }

        return false;
    }

    @Override
    public boolean habboHasAccountPermissions(IHabbo habbo, String[] permissions) {
        return this.habboHasAccountPermissions(habbo, permissions, null);
    }

    @Override
    public boolean habboHasAccountPermissions(IHabbo habbo, String[] permissions, IRoom room) {
        final IPermission habboPermission = this.getPermissionById(habbo.getData().getRank());

        if(habboPermission == null) return false;

        boolean hasPermission = true;

        for(final String permName : permissions) {
            if(!hasPermission) break;

            final PermissionType permissionType = habboPermission.getAccountPermission(permName);

            if (permissionType != PermissionType.ALLOWED) {
                hasPermission = false;
            }

            if(permissionType == PermissionType.RIGHTS) {
                // TODO: Check room rights
                hasPermission = room != null && room.getData().getOwnerId() == habbo.getData().getId();
            }
        }

        return hasPermission;
    }

    @Override
    public boolean habboHasAccountPermission(IHabbo habbo, String permission) {
        return this.habboHasAccountPermission(habbo, permission, null);
    }

    @Override
    public boolean habboHasAccountPermission(IHabbo habbo, String permission, IRoom room) {
        final IPermission habboPermission = this.getPermissionById(habbo.getData().getRank());

        if(habboPermission == null) return false;

        final PermissionType permissionType = habboPermission.getCommandPermission(permission);

        if(permissionType == PermissionType.ALLOWED) return true;

        if(permissionType == PermissionType.RIGHTS) {
            // TODO: Check room rights
            return room != null && room.getData().getOwnerId() == habbo.getData().getId();
        }

        return false;
    }

    private void loadPermissionsFromDatabase() {
        this.repository.loadAllPermissions(result -> {
            if(result == null) return;

            final IPermission permission = new Permission(result);

            this.permissions.putIfAbsent(permission.getId(), permission);
        });

        this.logger.debug(STR."[\{this.permissions.size()}] permissions loaded successfully.");
    }
}
