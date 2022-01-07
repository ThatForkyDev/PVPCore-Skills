package games.trident.skills.conf;

import com.google.common.collect.Maps;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.ConfigurableExperience;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

//tridentg_development
//tridentg_admin
//Thisisanadminpassword!
public class ConfMining extends ConfSkill {
    @Getter private HashMap<Material, ConfigurableExperience> experience = Maps.newHashMap();

    public ConfMining(SkillsPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("mining"));

        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("mining").getConfigurationSection("experience").getKeys(false)) {
            experience.put(Material.valueOf(key.toUpperCase()), new ConfigurableExperience(config.getDouble("mining.experience." + key + ".experience")));
        }
    }
}
