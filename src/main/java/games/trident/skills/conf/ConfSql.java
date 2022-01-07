package games.trident.skills.conf;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ConfSql {
    private String host;
    private int port;
    private String databaseName;
    private String user;
    private String password;
    private int minIdle;
    private int maxConnections;

    public ConfSql(ConfigurationSection section) {
        this.host = section.getString("host");
        this.port = section.getInt("port");
        this.databaseName = section.getString("databaseName");
        this.user = section.getString("user");
        this.password = section.getString("password");
        this.minIdle = section.getInt("minIdle");
        this.maxConnections = section.getInt("maxConnections");
    }
}
