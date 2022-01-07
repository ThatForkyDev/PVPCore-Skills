package games.trident.skills.conf.configurable;

import games.trident.skills.conf.configurable.levelup.ConfigurableMessage;
import games.trident.skills.conf.configurable.levelup.ConfigurableSound;
import games.trident.skills.conf.configurable.levelup.ConfigurableTitle;
import org.bukkit.configuration.ConfigurationSection;

public class ConfigurableLevelUp {
    private ConfigurableMessage message;
    private ConfigurableTitle title;
    private ConfigurableSound sound;

    public ConfigurableLevelUp(ConfigurationSection section) {

    }
}
