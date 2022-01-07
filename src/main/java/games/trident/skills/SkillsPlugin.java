package games.trident.skills;

import games.trident.skills.command.SkillsCommand;
import games.trident.skills.database.BaseDatabase;
import games.trident.skills.listeners.FishingListeners;
import games.trident.skills.listeners.MiningListeners;
import games.trident.skills.listeners.ProfileListeners;
import games.trident.skills.managers.ConfManager;
import games.trident.skills.managers.ProfileManager;
import games.trident.skills.utilities.menu.MenuAPI;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class SkillsPlugin extends JavaPlugin {
    @Getter private ConfManager confManager;
    @Getter private ProfileManager profileManager;
    @Getter private static SkillsPlugin API;

    @Override
    public void onEnable() {
        MenuAPI.onEnable(this);

        saveDefaultConfig();
        this.confManager = new ConfManager(this);
        this.profileManager = new ProfileManager();

        new FishingListeners(this);
        new MiningListeners(this);
        new ProfileListeners(this);

        new SkillsCommand(this);

        initialiseSQL();
        API = this;
    }

    private void initialiseSQL() {
        BaseDatabase.getInstance().init(confManager.getConfSql());
        BaseDatabase.createTables(SkillsPlugin.class, "create_tables");
    }
}
