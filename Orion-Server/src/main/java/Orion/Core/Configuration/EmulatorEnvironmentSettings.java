package Orion.Core.Configuration;

import Orion.Api.Server.Core.Configuration.IEmulatorEnvironmentSettings;
import com.google.inject.Singleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Singleton
public class EmulatorEnvironmentSettings implements IEmulatorEnvironmentSettings {
    private final Properties settings;

    private final Logger logger = LogManager.getLogger();

    public EmulatorEnvironmentSettings() {
        this.settings = new Properties();
    }

    @Override
    public void initialize() {
        this.loadEnvironmentSettings();
    }

    private void loadEnvironmentSettings() {
        try {
            final InputStreamReader stream = new InputStreamReader(new FileInputStream("./configuration/config.properties"), StandardCharsets.UTF_8);

            this.settings.load(stream);
            stream.close();

            this.logger.debug(STR."[\{this.settings.size()}] environment settings loaded successfully.");
        } catch (FileNotFoundException e) {
            this.logger.error("Failed to load environment settings: config.properties not found.");
            System.exit(1);
        } catch (IOException e) {
            this.logger.error(STR."Failed to load environment settings: \{e.getMessage()}");
            System.exit(1);
        }
    }

    @Override
    public String getStringOrDefault(String key, String defaultValue) {
        return this.settings.getProperty(key, defaultValue);
    }

    @Override
    public String getString(String key) {
        return this.getStringOrDefault(key, null);
    }

    @Override
    public boolean getBooleanOrDefault(String key, boolean defaultValue) {
        try {
            return Boolean.parseBoolean(this.getStringOrDefault(key, Boolean.toString(defaultValue)));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse boolean value for key \{key}: \{e.getMessage()}");
        }

        return defaultValue;
    }

    @Override
    public boolean getBoolean(String key) {
        return this.getBooleanOrDefault(key, false);
    }

    @Override
    public int getIntegerOrDefault(String key, int defaultValue) {
        try {
            return Integer.parseInt(this.getStringOrDefault(key, Integer.toString(defaultValue)));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse integer value for key \{key}: \{e.getMessage()}");
        }

        return defaultValue;
    }

    @Override
    public int getInteger(String key) {
        return this.getIntegerOrDefault(key, 0);
    }

    @Override
    public double getDoubleOrDefault(String key, double defaultValue) {
        try {
            return Double.parseDouble(this.getStringOrDefault(key, Double.toString(defaultValue)));
        } catch (Exception e) {
            this.logger.error(STR."Failed to parse double value for key \{key}: \{e.getMessage()}");
        }

        return defaultValue;
    }

    @Override
    public double getDouble(String key) {
        return this.getDoubleOrDefault(key, 0.0);
    }
}
