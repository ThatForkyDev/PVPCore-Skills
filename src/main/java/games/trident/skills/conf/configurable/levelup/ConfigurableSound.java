package games.trident.skills.conf.configurable.levelup;

import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@Getter
public class ConfigurableSound {
    private boolean enabled;
    private Sound sound;
    private float pitch;
    private float volume;

    public ConfigurableSound(ConfigurationSection section) {
        this.enabled = section.getBoolean("enabled");
        this.sound = Sound.valueOf(section.getString("sound").toUpperCase());
        this.pitch = section.getFloat("pitch");
        this.volume = section.getFloat("volume");
    }

    public void send(Player player) {
        if (enabled) {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }
    }
}
