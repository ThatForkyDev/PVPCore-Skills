package games.trident.skills.conf;

import com.google.common.collect.Maps;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.ConfigurableReward;
import games.trident.skills.utilities.RandomCollection;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfRewards {
    private SkillsPlugin plugin;
    private FileConfiguration file;

    @Getter private RandomCollection<ConfigurableReward> rewardsCollection = new RandomCollection<>();
    @Getter private HashMap<String, ConfigurableReward> rewards = Maps.newHashMap();

    public ConfRewards(SkillsPlugin plugin) {
        this.plugin = plugin;
        this.file = plugin.getConfig();

        reloadConfigurableRewards();
    }

    public void reloadConfigurableRewards() {
        this.rewards = Maps.newHashMap();
        this.rewardsCollection = new RandomCollection<>();

        for (String reward : file.getConfigurationSection("rewards").getKeys(false)) {
            String name = file.getString("rewards." + reward + ".name");
            double chance = file.getDouble("rewards." + reward + ".chance");
            String command = file.getString("rewards." + reward + ".command");

            ConfigurableReward configurableReward = new ConfigurableReward(name, chance, command);
            rewards.put(reward, configurableReward);
            rewardsCollection.add(chance, configurableReward);
        }
    }
}
