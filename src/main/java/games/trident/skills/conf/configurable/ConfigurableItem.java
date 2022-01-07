package games.trident.skills.conf.configurable;

import com.google.common.collect.Lists;
import games.trident.skills.utilities.ItemBuilder;
import games.trident.skills.utilities.Placeholder;
import games.trident.skills.utilities.PlaceholderUtil;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.ItemBucket;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

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

    public ItemStack build(Placeholder[] placeholders) {
        return new ItemBuilder(material)
                .setName(PlaceholderUtil.replacePlaceholders(name, placeholders))
                .setLoreArray(PlaceholderUtil.replacePlaceholders(lore, placeholders), true)
                .build();
    }
}
