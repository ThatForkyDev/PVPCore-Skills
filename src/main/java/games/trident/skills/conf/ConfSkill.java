package games.trident.skills.conf;

import com.google.common.collect.Maps;
import games.trident.skills.conf.configurable.ConfigurableItem;
import games.trident.skills.conf.configurable.ConfigurableLevelUp;
import games.trident.skills.conf.configurable.ConfigurablePool;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;

@Getter
public class ConfSkill {
    private String name;
    private ConfigurableItem item;
    private ConfigurableLevelUp configurableLevelUp;
    private ConfigurablePool configurablePool;
    private HashMap<Integer, List<String>> levels = Maps.newHashMap();

    public ConfSkill(ConfigurationSection section) {
        this.name = section.getString("name");
        this.item = new ConfigurableItem(section.getConfigurationSection("item"));
        this.configurableLevelUp = new ConfigurableLevelUp(section.getConfigurationSection("levelup"));
        this.configurablePool = new ConfigurablePool(section.getConfigurationSection("finalPool"));

        for (String key : section.getConfigurationSection("levels").getKeys(false)) {
            int level = Integer.valueOf(key);
            List<String> rewards = section.getStringList("mining." + level + ".rewards");

            levels.put(level, rewards);
        }
    }
}
