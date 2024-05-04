package Orion.Api.Server.Core.Configuration;

import Orion.Api.Util.Initializable;

public interface IEmulatorEnvironmentSettings extends Initializable {
    String getStringOrDefault(String key, String defaultValue);

    boolean getBooleanOrDefault(String key, boolean defaultValue);

    int getIntegerOrDefault(String key, int defaultValue);

    double getDoubleOrDefault(String key, double defaultValue);

    String getString(String key);

    boolean getBoolean(String key);

    int getInteger(String key);

    double getDouble(String key);
}
