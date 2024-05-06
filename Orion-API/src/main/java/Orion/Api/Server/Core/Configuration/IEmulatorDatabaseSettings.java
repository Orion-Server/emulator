package Orion.Api.Server.Core.Configuration;

import Orion.Api.Util.Initializable;

public interface IEmulatorDatabaseSettings extends Initializable {
    void forceReload();

    String getSetting(String key);

    String getSettingOrDefault(String key, String defaultValue);

    boolean getBooleanOrDefault(String key, boolean defaultValue);

    int getIntegerOrDefault(String key, int defaultValue);

    double getDoubleOrDefault(String key, double defaultValue);
}
