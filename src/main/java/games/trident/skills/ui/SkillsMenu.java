package games.trident.skills.ui;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.skill.ConfFarming;
import games.trident.skills.conf.configurable.skill.ConfFishing;
import games.trident.skills.conf.configurable.skill.ConfMining;
import games.trident.skills.conf.configurable.skill.ConfSlaying;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.type.farming.FarmingLevel;
import games.trident.skills.type.fishing.FishingLevel;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.type.slaying.SlayingLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.Placeholder;
import games.trident.skills.utilities.TextUtil;
import games.trident.skills.utilities.menu.Menu;
import games.trident.skills.utilities.menu.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SkillsMenu extends Menu {
    public SkillsMenu(SkillsPlugin plugin, Player player) {
        super(plugin.getConfManager().getConfMenu().getTitle(), plugin.getConfManager().getConfMenu().getRows());

        ProfileSkills profile = plugin.getProfileManager().getProfile(player.getUniqueId());

        {
            Level level = profile.getLevableEntry("farming", new FarmingLevel(1, 0));

            ConfFarming confFarming = plugin.getConfManager().getConfFarming();
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(level.getLevel())),
                    new Placeholder("%next_level%", Integer.toString(level.getNextLevel())),
                    new Placeholder("%exp%", Double.toString(level.getExperience())),
                    new Placeholder("%exp_required%", Double.toString(level.getRequiredExp())),
                    new Placeholder("%progress_bar%", TextUtil.getBar(ChatColor.GREEN.toString(), ChatColor.GRAY.toString(), (level.getExperience() - level.getPreviousRequiredExp()), (level.getRequiredExp() - level.getPreviousRequiredExp()), "|", 25)),
            };

            addMenuItem(new MenuItem(confFarming.getItem().build(placeholders)), confFarming.getItem().getSlot());
        }

        {
            Level level = profile.getLevableEntry("fishing", new FishingLevel(1, 0));

            ConfFishing confFishing = plugin.getConfManager().getConfFishing();
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(level.getLevel())),
                    new Placeholder("%next_level%", Integer.toString(level.getNextLevel())),
                    new Placeholder("%exp%", Double.toString(level.getExperience())),
                    new Placeholder("%exp_required%", Double.toString(level.getRequiredExp())),
                    new Placeholder("%progress_bar%", TextUtil.getBar(ChatColor.GREEN.toString(), ChatColor.GRAY.toString(), (level.getExperience() - level.getPreviousRequiredExp()), (level.getRequiredExp() - level.getPreviousRequiredExp()), "|", 25)),
            };

            addMenuItem(new MenuItem(confFishing.getItem().build(placeholders)), confFishing.getItem().getSlot());
        }

        {
            Level level = profile.getLevableEntry("mining", new MiningLevel(1, 0));

            ConfMining confMining = plugin.getConfManager().getConfMining();
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(level.getLevel())),
                    new Placeholder("%next_level%", Integer.toString(level.getNextLevel())),
                    new Placeholder("%exp%", Double.toString(level.getExperience())),
                    new Placeholder("%exp_required%", Double.toString(level.getRequiredExp())),
                    new Placeholder("%progress_bar%", TextUtil.getBar(ChatColor.GREEN.toString(), ChatColor.GRAY.toString(), (level.getExperience() - level.getPreviousRequiredExp()), (level.getRequiredExp() - level.getPreviousRequiredExp()), "|", 25)),
            };

            addMenuItem(new MenuItem(confMining.getItem().build(placeholders)), confMining.getItem().getSlot());
        }

        {
            Level level = profile.getLevableEntry("slaying", new SlayingLevel(1, 0));

            ConfSlaying confSlaying = plugin.getConfManager().getConfSlaying();
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(level.getLevel())),
                    new Placeholder("%next_level%", Integer.toString(level.getNextLevel())),
                    new Placeholder("%exp%", Double.toString(level.getExperience())),
                    new Placeholder("%exp_required%", Double.toString(level.getRequiredExp())),
                    new Placeholder("%progress_bar%", TextUtil.getBar(ChatColor.GREEN.toString(), ChatColor.GRAY.toString(), (level.getExperience() - level.getPreviousRequiredExp()), (level.getRequiredExp() - level.getPreviousRequiredExp()), "|", 25)),
            };

            addMenuItem(new MenuItem(confSlaying.getItem().build(placeholders)), confSlaying.getItem().getSlot());
        }

        ItemStack spacerItem = plugin.getConfManager().getConfMenu().getSpacerItem().build(new Placeholder[0]);

        for (int i = 0; i < getInventory().getSize(); i++) {
            addMenuItem(new MenuItem(spacerItem), i);
        }
    }
}
