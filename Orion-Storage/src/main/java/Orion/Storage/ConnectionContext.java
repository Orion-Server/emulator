package Orion.Storage;

import Orion.Api.Storage.IConnectionContext;
import Orion.Api.Storage.Providers.IConnectionProvider;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ConnectionContext implements IConnectionContext {
    @Inject
    private IConnectionProvider connectionProvider;

    public IConnectionProvider getProvider() {
        return connectionProvider;
    }
}
