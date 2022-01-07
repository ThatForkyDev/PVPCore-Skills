package games.trident.skills.conf.configurable.ability.skill;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ConfigurableEnchantment {
    private int levelInterval;
    private Enchantment enchantment;
    private int levels;

    public ConfigurableEnchantment(ConfigurationSection section) {
        this.levelInterval = section.getInt("levelInterval");
        this.enchantment = Enchantment.getByName(section.getString("enchantment"));
        this.levels = section.getInt("levels");
    }

    public void add(int level, ItemStack stack) {
        if (level % levelInterval == 0) {
            String name = stack.getType().name();

            if (name.endsWith("_PICKAXE") || name.endsWith("_HOE") || name.endsWith("_AXE") || name.endsWith("_SHOVEL")) {
                if (stack.containsEnchantment(enchantment))
                    stack.addUnsafeEnchantment(enchantment, stack.getEnchantmentLevel(enchantment) + levels);
                else
                    stack.addUnsafeEnchantment(enchantment, levels);
            }
        }
    }
}
