package games.trident.skills.conf;

import games.trident.skills.conf.configurable.ConfigurableItem;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ConfMenu {
    private String title;
    private int rows;
    private ConfigurableItem spacerItem;

    public ConfMenu(ConfigurationSection section) {
        this.title = section.getString("title");
        this.rows = section.getInt("rows");
        this.spacerItem = new ConfigurableItem(section.getConfigurationSection("spacerItem"));
    }
}
