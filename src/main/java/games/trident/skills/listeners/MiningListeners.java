package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.profile.entry.LevelableEntry;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
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
        Level entry = profileSkills.getLevableEntry("mining", new MiningLevel(1, 0));

        if (entry.addExp(100)) {
            player.sendMessage("Level Up");
        } else {
            player.sendMessage("Not levelved up yet;");
        }
    }
}
