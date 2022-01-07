package games.trident.skills.conf.configurable;

import com.google.common.collect.Lists;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class ConfigurablePool {
    private int level;
    private int interval;
    private List<String> possibleRewards = Lists.newArrayList();

    public ConfigurablePool(ConfigurationSection section) {

    }
}
