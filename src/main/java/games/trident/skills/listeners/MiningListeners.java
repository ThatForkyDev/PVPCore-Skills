package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfMining;
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
import org.bukkit.event.block.BlockPlaceEvent;

public class MiningListeners implements Listener {
    private SkillsPlugin plugin;

    public MiningListeners(SkillsPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled())
            return;

        Material material = event.getBlock().getType();

        Player player = event.getPlayer();
        ProfileSkills profileSkills = plugin.getProfileManager().getProfile(player.getUniqueId());
        Level entry = profileSkills.getLevableEntry("mining", new MiningLevel(1, 0));
        ConfMining confMining = plugin.getConfManager().getConfMining();

        if (!confMining.getExperience().containsKey(material))
            return;

        if (entry.addExp(confMining.getExperience().get(material).getExperience())) {
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(entry.getLevel()))
            };

            confMining.getConfigurableLevelUp().send(player, placeholders);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        for (int i = 1; i < 100; i++) {
            MiningLevel level = new MiningLevel(i, 0);
            event.getPlayer().sendMessage(i + ": " + level.getRequiredExp());
        }
    }
}
