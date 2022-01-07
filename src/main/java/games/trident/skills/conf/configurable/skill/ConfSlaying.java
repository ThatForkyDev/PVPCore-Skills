package games.trident.skills.conf.configurable.skill;

import com.google.common.collect.Maps;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfSkill;
import games.trident.skills.conf.configurable.ConfigurableExperience;
import games.trident.skills.conf.configurable.ability.ConfigurableChanceAbility;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfSlaying extends ConfSkill {
    @Getter private HashMap<String, ConfigurableExperience> experience = Maps.newHashMap();
    @Getter private HashMap<String, ConfigurableChanceAbility> abilities = Maps.newHashMap();

    public ConfSlaying(SkillsPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("slaying"));

        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("slaying").getConfigurationSection("experience").getKeys(false)) {
            experience.put(key, new ConfigurableExperience(config.getDouble("slaying.experience." + key + ".experience")));
        }

        for (String key : config.getConfigurationSection("slaying").getConfigurationSection("chances").getKeys(false)) {
            abilities.put(key, new ConfigurableChanceAbility(config.getConfigurationSection("slaying.chances." + key)));
        }
    }
}
