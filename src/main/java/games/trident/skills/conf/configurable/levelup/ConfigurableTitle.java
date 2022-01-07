package games.trident.skills.conf.configurable.levelup;

import games.trident.skills.utilities.Placeholder;
import games.trident.skills.utilities.PlaceholderUtil;
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
        this.fade = section.getInt("fade") * 20;
        this.stay = section.getInt("stay") * 20;
    }

    public void send(Player player, Placeholder[] placeholders) {
        if (enabled)
            player.sendTitle(new Title(ChatColor.translateAlternateColorCodes('&', PlaceholderUtil.replacePlaceholders(title, placeholders)), ChatColor.translateAlternateColorCodes('&', PlaceholderUtil.replacePlaceholders(subtitle, placeholders)), fade, stay, fade));
    }
}
