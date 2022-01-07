package games.trident.skills.conf.configurable.ability.skill;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class ConfigurableBlastRadius {
    private List<Material> whitelist = Lists.newArrayList();
    private int x, y, z;

    public ConfigurableBlastRadius(ConfigurationSection section) {
        for (String s : section.getStringList("whitelist")) {
            whitelist.add(Material.valueOf(s.toUpperCase()));
        }

        this.x = section.getInt("x");
        this.y = section.getInt("y");
        this.z = section.getInt("z");
    }

    public void proc(Block block, ItemStack stack) {
        for (int aX = block.getX() - x; aX <= block.getX() + x; aX++) {
            for (int aY = block.getY() - y; aY <= block.getY() + y; aY++) {
                for (int aZ = block.getZ() - z; aZ <= block.getZ() + z; aZ++) {
                    Block tempBlock = block.getWorld().getBlockAt(aX, aY, aZ);

                    if (whitelist.contains(tempBlock.getType())) {
                        tempBlock.breakNaturally(stack);
                    }
                }
            }
        }
    }
}
