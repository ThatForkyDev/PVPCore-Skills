package games.trident.skills.conf.configurable.levelup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@AllArgsConstructor @Getter
public class ConfigurableSound {
    private boolean enabled;
    private Sound sound;
    private float pitch;
    private float volume;

    public void send(Player player) {
        if (enabled) {
            player.playSound(player.getLocation(), sound, volume, pitch);
        }
    }
}
