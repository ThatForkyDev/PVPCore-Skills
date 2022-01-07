package games.trident.skills.conf.configurable;

import games.trident.skills.conf.configurable.levelup.ConfigurableMessage;
import games.trident.skills.conf.configurable.levelup.ConfigurableSound;
import games.trident.skills.conf.configurable.levelup.ConfigurableTitle;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@Getter
public class ConfigurableLevelUp {
    private ConfigurableMessage message;
    private ConfigurableTitle title;
    private ConfigurableSound sound;

    public ConfigurableLevelUp(ConfigurationSection section) {
        this.message = new ConfigurableMessage(section.getConfigurationSection("message"));
        this.title = new ConfigurableTitle(section.getConfigurationSection("title"));
        this.sound = new ConfigurableSound(section.getConfigurationSection("sound"));
    }

    public void send(Player player) {
        message.send(player);
        title.send(player);
        sound.send(player);
    }
}
