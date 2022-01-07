package games.trident.skills.conf.configurable.levelup;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ConfigurableMessage {
    private boolean enabled;
    private String message;

    public ConfigurableMessage(ConfigurationSection section) {
        this.enabled = section.getBoolean("enabled");
        this.message = section.getString("message");
    }

    public void send(Player player) {
        if (enabled)
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
