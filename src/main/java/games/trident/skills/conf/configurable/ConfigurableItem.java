package games.trident.skills.conf.configurable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

import java.util.List;

@Getter @AllArgsConstructor
public class ConfigurableItem {
    private Material material;
    private int slot;
    private String name;
    private List<String> lore;
}
