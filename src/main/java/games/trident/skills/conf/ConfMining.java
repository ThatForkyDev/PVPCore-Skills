package games.trident.skills.conf;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.ConfigurableItem;
import games.trident.skills.conf.configurable.ConfigurableLevelUp;
import games.trident.skills.conf.configurable.ConfigurablePool;

import java.util.HashMap;
import java.util.List;

public class ConfMining extends ConfSkill {
    public ConfMining(SkillsPlugin plugin) {
        super(plugin.getConfig().getConfigurationSection("mining"));
    }
}
