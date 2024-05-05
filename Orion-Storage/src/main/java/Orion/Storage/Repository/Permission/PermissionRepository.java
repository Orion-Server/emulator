package Orion.Storage.Repository.Permission;

import Orion.Api.Storage.Repository.Permission.IPermissionRepository;
import Orion.Api.Storage.Result.IConnectionResultConsumer;
import Orion.Storage.Query.Permission.PermissionQuery;
import Orion.Storage.Repository.DatabaseRepository;
import com.google.inject.Singleton;

@Singleton
public class PermissionRepository extends DatabaseRepository implements IPermissionRepository {
    public void loadAllPermissions(IConnectionResultConsumer consumer) {
        this.select(PermissionQuery.SELECT_ALL_PERMISSIONS.get(), consumer);
    }
}
