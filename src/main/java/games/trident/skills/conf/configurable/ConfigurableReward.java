package games.trident.skills.conf.configurable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ConfigurableReward {
    private String name;
    private double chance;
    private String command;
}
