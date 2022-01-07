package games.trident.skills.conf.configurable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@AllArgsConstructor @Getter
public class ConfigurableReward {
    private String name;
    private double chance;
    private String command;

    public void give(Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
    }
}
