package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.ability.ConfigurableChanceAbility;
import games.trident.skills.conf.configurable.skill.ConfMining;
import games.trident.skills.events.SkillLevelupEvent;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.type.mining.MiningLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.Placeholder;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;
import java.util.Objects;

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

        if (Objects.isNull(profileSkills))
            return;

        Level entry = profileSkills.getLevableEntry("mining", new MiningLevel(1, 0));
        ConfMining confMining = plugin.getConfManager().getConfMining();

        for (Map.Entry<String, ConfigurableChanceAbility> mapEntry : plugin.getConfManager().getConfMining().getAbilities().entrySet()) {
            String key = mapEntry.getKey();

            if (mapEntry.getValue().proc(entry.getLevel())) {
                if (key.equalsIgnoreCase("BLAST_RADIUS")) {
                    plugin.getConfManager().getConfMining().getConfigurableBlastRadius().proc(event.getBlock(), event.getPlayer().getItemInHand());
                }
            }
        }

        if (!confMining.getExperience().containsKey(material))
            return;

        double experience = confMining.getExperience().get(material).getExperience();

        if (entry.addExp(experience)) {
            Placeholder[] placeholders = {
                    new Placeholder("%player%", player.getName()),
                    new Placeholder("%level%", Integer.toString(entry.getLevel()))
            };

            confMining.getConfigurableLevelUp().send(player, placeholders);

            SkillLevelupEvent skillLevelupEvent = new SkillLevelupEvent(player, "mining", entry.getLevel());
            Bukkit.getPluginManager().callEvent(skillLevelupEvent);
        }
    }

    @EventHandler
    public void onLevelUp(SkillLevelupEvent event) {
        if (event.getType().equalsIgnoreCase("mining")) {
            ConfMining confMining = plugin.getConfManager().getConfMining();

            confMining.getFortuneEnchantment().add(event.getLevel(), event.getPlayer().getItemInHand());
            confMining.getEfficiencyEnchantment().add(event.getLevel(), event.getPlayer().getItemInHand());
        }
    }
}
