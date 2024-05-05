package Orion.Storage.Result;

import Orion.Api.Storage.Result.IConnectionResult;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ConnectionResult implements IConnectionResult {
    private final ResultSet result;

    public ConnectionResult(final ResultSet result) {
        this.result = result;
    }

    @Override
    public String getString(String name) throws Exception {
        return this.result.getString(name);
    }

    @Override
    public int getInt(String name) throws Exception {
        return this.result.getInt(name);
    }

    @Override
    public int getInt(int columnIndex) throws Exception {
        return result.getInt(columnIndex);
    }

    @Override
    public long getLong(String name) throws Exception {
        return this.result.getLong(name);
    }

    @Override
    public long getLong(int columnIndex) throws Exception {
        return result.getLong(columnIndex);
    }

    @Override
    public boolean getBoolean(String name) throws Exception {
        return this.result.getBoolean(name);
    }

    @Override
    public double getDouble(String name) throws Exception {
        return this.result.getDouble(name);
    }

    public boolean hasColumn(String name) throws Exception {
        return this.result.findColumn(name) > 0;
    }

    public boolean isNull(String name) throws Exception {
        return this.result.getObject(name) == null;
    }

    public ResultSetMetaData getMetaData() throws Exception {
        return this.result.getMetaData();
    }
}

