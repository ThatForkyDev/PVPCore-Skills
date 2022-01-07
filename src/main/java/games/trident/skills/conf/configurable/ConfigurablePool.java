package games.trident.skills.conf.configurable;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

@Getter
public class ConfigurablePool {
    private int level;
    private int interval;
    private List<String> possibleRewards;

    public ConfigurablePool(ConfigurationSection section) {
        this.level = section.getInt("level");
        this.interval = section.getInt("interval");
        this.possibleRewards = section.getStringList("rewards");
    }
}
