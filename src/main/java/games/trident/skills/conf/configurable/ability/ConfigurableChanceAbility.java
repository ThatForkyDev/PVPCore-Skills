package games.trident.skills.conf.configurable.ability;

import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class ConfigurableChanceAbility {
    private double defaultChance;
    private double incrementPerLevel;
    private double maxChance;

    public ConfigurableChanceAbility(ConfigurationSection section) {
        this.defaultChance = section.getDouble("defaultChance");
        this.incrementPerLevel = section.getDouble("incrementPerLevel");
        this.maxChance = section.getDouble("maxChance");
    }

    public boolean proc(int level) {
        double random = ThreadLocalRandom.current().nextDouble(100);
        double equation = defaultChance + (incrementPerLevel * level);

        if (equation > level)
            equation = level;

        return random < equation;
    }
}
