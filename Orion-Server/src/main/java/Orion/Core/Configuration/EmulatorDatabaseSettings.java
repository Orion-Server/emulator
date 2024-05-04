package Orion.Core.Configuration;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;

import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;

@Singleton
public class EmulatorDatabaseSettings implements IEmulatorDatabaseSettings {
    private boolean isLoaded = false;

    private final THashMap<String, String> settings;

    public EmulatorDatabaseSettings() {
        this.settings = new THashMap<>();

        this.loadEnvironmentSettings(false);
    }

    private void loadEnvironmentSettings(boolean forceReload) {
        if(this.isLoaded && !forceReload) return;

        // Load settings from environment
        this.isLoaded = true;
    }

    public void forceReload() {
        this.loadEnvironmentSettings(true);
    }

    public String getSetting(String key) {
        return this.settings.get(key);
    }

    public boolean getBooleanOrDefault(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(this.getSetting(key));
        } catch (Exception e) {
            // log error
        }

        return defaultValue;
    }

    public int getIntegerOrDefault(String key, int defaultValue) {
        try {
            return Integer.parseInt(this.getSetting(key));
        } catch (Exception e) {
            // log error
        }

        return defaultValue;
    }

    public double getDoubleOrDefault(String key, double defaultValue) {
        try {
            return Double.parseDouble(this.getSetting(key));
        } catch (Exception e) {
            // log error
        }

        return defaultValue;
    }
}
