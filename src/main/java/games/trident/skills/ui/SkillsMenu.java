package games.trident.skills.ui;

import com.sun.management.internal.VMOptionCompositeData;
import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.ConfMining;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.Placeholder;
import games.trident.skills.utilities.TextUtil;
import games.trident.skills.utilities.menu.Menu;
import games.trident.skills.utilities.menu.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SkillsMenu extends Menu {
    public SkillsMenu(SkillsPlugin plugin, Player player) {
        super("Skills", 3);

        ProfileSkills profile = plugin.getProfileManager().getProfile(player.getUniqueId());

        {
            Level level = profile.getLevableEntry("mining", new MiningLevel(1, 0));

            ConfMining confMining = plugin.getConfManager().getConfMining();
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(level.getLevel())),
                    new Placeholder("%next_level%", Integer.toString(level.getNextLevel())),
                    new Placeholder("%exp%", Double.toString(level.getExperience())),
                    new Placeholder("%exp_required%", Double.toString(level.getRequiredExp())),
                    new Placeholder("%progress_bar%", TextUtil.getBar(ChatColor.GREEN.toString(), ChatColor.GRAY.toString(), (float) (level.getExperience() - level.getPreviousRequiredExp()), (float) (level.getRequiredExp() - level.getPreviousRequiredExp()), "|", 25)),
            };

            addMenuItem(new MenuItem(confMining.getItem().build(placeholders)), confMining.getItem().getSlot());
        }
    }
}
