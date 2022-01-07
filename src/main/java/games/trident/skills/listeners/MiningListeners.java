package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.profile.ProfileSkills;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MiningListeners implements Listener {
    private SkillsPlugin plugin;

    public MiningListeners(SkillsPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled())
            return;

        Player player = event.getPlayer();
        ProfileSkills profileSkills = plugin.getProfileManager().getProfile(player.getUniqueId());
    }
}
