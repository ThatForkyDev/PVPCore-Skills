package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import games.trident.skills.conf.configurable.ability.ConfigurableChanceAbility;
import games.trident.skills.conf.configurable.skill.ConfSlaying;
import games.trident.skills.events.SkillLevelupEvent;
import games.trident.skills.profile.ProfileSkills;
import games.trident.skills.type.slaying.SlayingLevel;
import games.trident.skills.utilities.Level;
import games.trident.skills.utilities.Placeholder;
import gcspawners.AdvancedEntityDeathEvent;
import gcspawners.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.Objects;

public class SlayingListeners implements Listener {
    private SkillsPlugin plugin;

    public SlayingListeners(SkillsPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFish(AdvancedEntityDeathEvent event) {
        if (event.getDamager() instanceof Player) {
            String entityType = event.getEntityType();
            Player player = (Player) event.getDamager();

            ProfileSkills profileSkills = plugin.getProfileManager().getProfile(player.getUniqueId());

            if (Objects.isNull(profileSkills))
                return;

            Level entry = profileSkills.getLevableEntry("slaying", new SlayingLevel(1, 0));
            ConfSlaying confSlaying = plugin.getConfManager().getConfSlaying();

            if (!confSlaying.getExperience().containsKey(entityType))
                return;


            for (Map.Entry<String, ConfigurableChanceAbility> mapEntry : plugin.getConfManager().getConfSlaying().getAbilities().entrySet()) {
                String key = mapEntry.getKey();

                if (mapEntry.getValue().proc(entry.getLevel())) {
                    if (key.equalsIgnoreCase("KILL_STACK")) {
                        int x = event.getLocation().getBlockX();
                        int y = event.getLocation().getBlockY();
                        int z = event.getLocation().getBlockZ();

                        gcspawners.CustomEntityInstance entity = Core.getEntityHandler().matchEntityByPos(x, y, z);
                        entity.kill();
                    }
                }
            }

            double experience = confSlaying.getExperience().get(entityType).getExperience();

            if (entry.addExp(experience)) {
                Placeholder[] placeholders = {
                        new Placeholder("%player%", player.getName()),
                        new Placeholder("%level%", Integer.toString(entry.getLevel()))
                };

                confSlaying.getConfigurableLevelUp().send(player, placeholders);

                SkillLevelupEvent skillLevelupEvent = new SkillLevelupEvent(player, "slaying", entry.getLevel());
                Bukkit.getPluginManager().callEvent(skillLevelupEvent);
            }
        }
    }
}
