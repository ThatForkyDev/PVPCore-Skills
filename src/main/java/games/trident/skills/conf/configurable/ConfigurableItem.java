package games.trident.skills.conf.configurable;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@Getter
public class ConfigurableItem {
    private Material material;
    private int slot;
    private String name;
    private List<String> lore = Lists.newArrayList();

    public ConfigurableItem(ConfigurationSection section) {
        this.material = Material.valueOf(section.getString("material").toUpperCase());
        this.slot = section.getInt("slot");
        this.name = ChatColor.translateAlternateColorCodes('&', section.getString("name"));
        for (String line : section.getStringList("lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }
    }
}
