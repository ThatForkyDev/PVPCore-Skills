package games.trident.skills.conf.configurable.levelup;

import lombok.AllArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class ConfigurableMessage {
    private boolean enabled;
    private String message;

    public void send(Player player) {
        if (enabled)
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
