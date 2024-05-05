package Orion.Boot.Utils;

import com.google.inject.Singleton;
import org.fusesource.jansi.Ansi;

@Singleton
public class EmulatorVersioning {
    private final String version = "0.1-ALPHA";

    private final String buildDate = "2024-05-02";

    private final String author = "Orion Team";

    private final String website = "https://orionprojects.net";

    private final String logo = """
               ___       _               _____                 _       _            \s
              / _ \\ _ __(_) ___  _ __   | ____|_ __ ___  _   _| | __ _| |_ ___  _ __\s
             | | | | '__| |/ _ \\| '_ \\  |  _| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|
             | |_| | |  | | (_) | | | | | |___| | | | | | |_| | | (_| | || (_) | |  \s
              \\___/|_|  |_|\\___/|_| |_| |_____|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|  \s
    """;

    public String getVersion() {
        return version;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getWebsite() {
        return website;
    }

    public String getLogo() {
        return logo;
    }

    public String getFullVersion() {
        return STR."Orion Emulator v\{version} (\{buildDate})";
    }

    public String getFullVersionWithAuthor() {
        return STR."\{this.getFullVersion()} by \{author}";
    }

    public String getFullVersionWithWebsite() {
        return STR."\{this.getFullVersionWithAuthor()} - \{website}";
    }

    public void showFullVersionWithWebsite() {
        final String separator = new StringBuilder().repeat("=", this.getFullVersionWithWebsite().length() + 10).toString();

        System.out.println(separator);
        System.out.println(STR."\{this.getLogo()}     \{this.getFullVersionWithWebsite()}");
        System.out.println(separator);
    }
}
