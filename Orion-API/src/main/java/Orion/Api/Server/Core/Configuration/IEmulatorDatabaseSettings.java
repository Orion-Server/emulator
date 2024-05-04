package Orion.Api.Server.Core.Configuration;

public interface IEmulatorDatabaseSettings {
    void forceReload();

    boolean getBooleanOrDefault(String key, boolean defaultValue);

    int getIntegerOrDefault(String key, int defaultValue);

    double getDoubleOrDefault(String key, double defaultValue);
}
