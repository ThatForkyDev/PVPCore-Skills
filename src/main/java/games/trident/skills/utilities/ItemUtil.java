package games.trident.skills.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {
    public static boolean isAirOrNull(ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }
}
