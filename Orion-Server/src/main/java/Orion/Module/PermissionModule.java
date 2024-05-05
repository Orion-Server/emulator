package Orion.Module;

import Orion.Api.Server.Game.Permission.IPermissionManager;
import Orion.Game.Permission.PermissionManager;
import com.google.inject.AbstractModule;

public class PermissionModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IPermissionManager.class).to(PermissionManager.class);
    }
}
