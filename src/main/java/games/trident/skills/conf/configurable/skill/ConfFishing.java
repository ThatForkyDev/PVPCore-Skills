package games.trident.skills.conf.configurable.skill;

import com.google.common.collect.Maps;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfSkill;
import games.trident.skills.conf.configurable.ConfigurableExperience;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfFishing extends ConfSkill {
    @Getter private HashMap<Material, ConfigurableExperience> experience = Maps.newHashMap();

    public ConfFishing(SkillsPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("fishing"));

        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("fishing").getConfigurationSection("experience").getKeys(false)) {
            experience.put(Material.valueOf(key.toUpperCase()), new ConfigurableExperience(config.getDouble("fishing.experience." + key + ".experience")));
        }
    }
}
