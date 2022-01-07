package games.trident.skills.conf.configurable.skill;

import com.google.common.collect.Maps;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfSkill;
import games.trident.skills.conf.configurable.ConfigurableExperience;
import games.trident.skills.conf.configurable.ability.ConfigurableChanceAbility;
import games.trident.skills.conf.configurable.ability.skill.ConfigurableBlastRadius;
import games.trident.skills.conf.configurable.ability.skill.ConfigurableEnchantment;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfMining extends ConfSkill {
    @Getter private HashMap<Material, ConfigurableExperience> experience = Maps.newHashMap();
    @Getter private HashMap<String, ConfigurableChanceAbility> abilities = Maps.newHashMap();
    @Getter private ConfigurableBlastRadius configurableBlastRadius;
    @Getter private ConfigurableEnchantment efficiencyEnchantment;
    @Getter private ConfigurableEnchantment fortuneEnchantment;

    public ConfMining(SkillsPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("mining"));

        FileConfiguration config = plugin.getConfig();
        for (String key : config.getConfigurationSection("mining").getConfigurationSection("experience").getKeys(false)) {
            experience.put(Material.valueOf(key.toUpperCase()), new ConfigurableExperience(config.getDouble("mining.experience." + key + ".experience")));
        }

        for (String key : config.getConfigurationSection("mining").getConfigurationSection("chances").getKeys(false)) {
            abilities.put(key, new ConfigurableChanceAbility(config.getConfigurationSection("mining.chances." + key)));
        }

        this.configurableBlastRadius = new ConfigurableBlastRadius(config.getConfigurationSection("mining.blastRadius"));
        this.efficiencyEnchantment = new ConfigurableEnchantment(config.getConfigurationSection("mining.enchantments.efficiency"));
        this.fortuneEnchantment = new ConfigurableEnchantment(config.getConfigurationSection("mining.enchantments.fortune"));
    }
}
