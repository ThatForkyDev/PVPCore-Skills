package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListeners implements Listener {
    private SkillsPlugin plugin;

    public ProfileListeners(SkillsPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getProfileManager().save(event.getPlayer().getUniqueId(), true);
    }
}
