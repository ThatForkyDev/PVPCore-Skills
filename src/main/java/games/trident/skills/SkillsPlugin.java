package games.trident.skills;

import games.trident.skills.managers.ConfManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillsPlugin extends JavaPlugin {
    @Getter private ConfManager confManager;
    @Getter private static SkillsPlugin API;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.confManager = new ConfManager(this);

        API = this;
    }
}
