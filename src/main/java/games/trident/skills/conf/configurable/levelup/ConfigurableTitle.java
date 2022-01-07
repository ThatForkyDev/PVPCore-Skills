package games.trident.skills.conf.configurable.levelup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;

@Getter
public class ConfigurableTitle {
    private boolean enabled;
    private String title;
    private String subtitle;
    private int fade;
    private int stay;

    public ConfigurableTitle(ConfigurationSection section) {
        this.enabled = section.getBoolean("enabled");
        this.title = section.getString("title");
        this.subtitle = section.getString("subtitle");
        this.fade = section.getInt("fade");
        this.stay = section.getInt("stay");
    }

    public void send(Player player) {
        if (enabled)
            player.sendTitle(new Title(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fade, stay, fade));
    }
}
