package games.trident.skills.conf.configurable.levelup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.github.paperspigot.Title;

@AllArgsConstructor @Getter
public class ConfigurableTitle {
    private boolean enabled;
    private String title;
    private String subtitle;
    private int fade;
    private int stay;

    public void send(Player player) {
        if (enabled)
            player.sendTitle(new Title(ChatColor.translateAlternateColorCodes('&', title), ChatColor.translateAlternateColorCodes('&', subtitle), fade, stay, fade));
    }
}
