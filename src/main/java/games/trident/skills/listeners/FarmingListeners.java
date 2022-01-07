package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.skill.ConfMining;
import games.trident.skills.events.SkillLevelupEvent;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Objects;

public class FarmingListeners implements Listener {
    private SkillsPlugin plugin;

    public FarmingListeners(SkillsPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFish(BlockBreakEvent event) {
        if (event.isCancelled())
            return;

        Material material = event.getBlock().getType();

        Player player = event.getPlayer();
        ProfileSkills profileSkills = plugin.getProfileManager().getProfile(player.getUniqueId());

        if (Objects.isNull(profileSkills))
            return;

        Level entry = profileSkills.getLevableEntry("farming", new MiningLevel(1, 0));
        ConfMining confMining = plugin.getConfManager().getConfMining();

        if (!confMining.getExperience().containsKey(material))
            return;

        double experience = confMining.getExperience().get(material).getExperience();

        if (entry.addExp(experience)) {
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(entry.getLevel()))
            };

            confMining.getConfigurableLevelUp().send(player, placeholders);

            SkillLevelupEvent skillLevelupEvent = new SkillLevelupEvent(player, "farming", entry.getLevel());
            Bukkit.getPluginManager().callEvent(skillLevelupEvent);
        }
    }
}
