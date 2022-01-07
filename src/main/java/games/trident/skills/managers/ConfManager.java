package games.trident.skills.managers;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfMining;
import games.trident.skills.conf.ConfRewards;
import lombok.Getter;

public class ConfManager {
    @Getter private ConfRewards confRewards;
    @Getter private ConfMining confMining;

    public ConfManager(SkillsPlugin plugin) {
        this.confRewards = new ConfRewards(plugin);
        this.confMining = new ConfMining(plugin);
    }
}
