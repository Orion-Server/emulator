package Orion.Api.Storage.Repository.Permission;

import Orion.Api.Storage.Result.IConnectionResultConsumer;

public interface IPermissionRepository {
    void loadAllPermissions(IConnectionResultConsumer consumer);
}
