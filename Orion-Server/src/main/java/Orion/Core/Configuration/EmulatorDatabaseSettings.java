package Orion.Core.Configuration;

import Orion.Api.Server.Core.Configuration.IEmulatorDatabaseSettings;

import Orion.Api.Storage.Repository.Emulator.IEmulatorRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import gnu.trove.map.hash.THashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Singleton
public class EmulatorDatabaseSettings implements IEmulatorDatabaseSettings {
    private final Logger logger = LogManager.getLogger();

    private boolean isLoaded = false;

    private final THashMap<String, String> settings;

    @Inject
    private IEmulatorRepository repository;

    public EmulatorDatabaseSettings() {
        this.settings = new THashMap<>();
    }

    @Override
    public void initialize() {
        this.loadEnvironmentSettings(false);
    }

    private void loadEnvironmentSettings(boolean forceReload) {
        if(this.isLoaded && !forceReload) return;

        this.repository.loadAllSettings(result -> {
            if(result == null) return;

            this.settings.putIfAbsent(result.getString("key"), result.getString("value"));
        });

        this.isLoaded = true;

        this.logger.debug(STR."[\{this.settings.size()}] database settings loaded successfully.");
    }

    @Override
    public void forceReload() {
        this.loadEnvironmentSettings(true);
    }

    @Override
    public String getSetting(String key) {
        return this.settings.get(key);
    }

    @Override
    public String getSettingOrDefault(String key, String defaultValue) {
        return this.settings.getOrDefault(key, defaultValue);
    }

    @Override
    public boolean getBooleanOrDefault(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(this.getSetting(key));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse boolean value for key: \{key}");
        }

        return defaultValue;
    }

    @Override
    public int getIntegerOrDefault(String key, int defaultValue) {
        try {
            return Integer.parseInt(this.getSetting(key));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse integer value for key: \{key}");
        }

        return defaultValue;
    }

    @Override
    public double getDoubleOrDefault(String key, double defaultValue) {
        try {
            return Double.parseDouble(this.getSetting(key));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse double value for key: \{key}");
        }

        return defaultValue;
    }
}
