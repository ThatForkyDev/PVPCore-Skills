package games.trident.skills.managers;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfMenu;
import games.trident.skills.conf.configurable.skill.ConfFarming;
import games.trident.skills.conf.configurable.skill.ConfFishing;
import games.trident.skills.conf.configurable.skill.ConfMining;
import games.trident.skills.conf.ConfRewards;
import games.trident.skills.conf.configurable.skill.ConfSlaying;
import games.trident.skills.conf.ConfSql;
import lombok.Getter;

public class ConfManager {
    @Getter private ConfRewards confRewards;
    @Getter private ConfMenu confMenu;

    @Getter private ConfFarming confFarming;
    @Getter private ConfFishing confFishing;
    @Getter private ConfMining confMining;
    @Getter private ConfSlaying confSlaying;

    @Getter private ConfSql confSql;

    public ConfManager(SkillsPlugin plugin) {
        this.confRewards = new ConfRewards(plugin);
        this.confMenu = new ConfMenu(plugin.getConfig().getConfigurationSection("menu"));

        this.confFarming = new ConfFarming(plugin);
        this.confFishing = new ConfFishing(plugin);
        this.confMining = new ConfMining(plugin);
        this.confSlaying = new ConfSlaying(plugin);
        this.confSql = new ConfSql(plugin.getConfig().getConfigurationSection("sql"));
    }
}
