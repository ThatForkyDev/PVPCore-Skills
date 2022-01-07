package games.trident.skills.listeners;

import games.trident.skills.SkillsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class FishingListeners implements Listener {
    private SkillsPlugin plugin;

    public FishingListeners(SkillsPlugin plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        event.getPlayer().sendMessage("Bite Chance: " + event.getHook().getBiteChance());

        event.getHook().setBiteChance(0.9);
    }
}
