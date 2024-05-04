package Orion.Api.Storage;

import java.sql.Connection;

public interface IConnection {
    public Connection get() throws Exception;

    public IConnectionContext getConnectionContext();
}
