package Orion.Storage;

import Orion.Api.Storage.IConnection;
import Orion.Api.Storage.IConnectionContext;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Connection implements IConnection {
    @Inject
    private IConnectionContext connectionContext;

    @Override
    public java.sql.Connection get() throws Exception {
        return connectionContext.getProvider().getConnection();
    }

    public IConnectionContext getConnectionContext() {
        return connectionContext;
    }
}

