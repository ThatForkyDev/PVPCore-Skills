package games.trident.skills.utilities;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemBuilder {
    private ItemStack itemstack;

    public ItemBuilder(Material material) {
        this.itemstack = new ItemStack(material);
    }

    public ItemBuilder(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public ItemBuilder setDurability(short durability) {
        itemstack.setDurability(durability);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        itemstack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        if (name == null) return this;

        ItemMeta meta = itemstack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = itemstack.getItemMeta();

        List<String> list = new ArrayList<>();
        for (String str : lore)
            list.add(ChatColor.translateAlternateColorCodes('&', str));

        meta.setLore(list);
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLoreArray(String[] lore) {
        ItemMeta meta = itemstack.getItemMeta();

        List<String> list = new ArrayList<>();
        for (String str : lore)
            list.add(ChatColor.translateAlternateColorCodes('&', str));

        meta.setLore(list);
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLoreArray(List<String> lore, boolean color) {
        ItemMeta meta = itemstack.getItemMeta();

        if (color) {
            List<String> list = new ArrayList<>();
            for (String str : lore)
                list.add(ChatColor.translateAlternateColorCodes('&', str));

            meta.setLore(list);
        } else {
            meta.setLore(lore);
        }

        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(int line, String lore) {
        ItemMeta meta = itemstack.getItemMeta();

        List<String> list = new ArrayList<>();
        if (meta.getLore() != null) list.addAll(meta.getLore());

        list.set(line, ChatColor.translateAlternateColorCodes('&', lore));

        meta.setLore(list);
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        ItemMeta meta = itemstack.getItemMeta();

        List<String> list = new ArrayList<>();
        if (meta.getLore() != null) {
            list.addAll(meta.getLore());
        }

        list.add(ChatColor.translateAlternateColorCodes('&', lore));

        meta.setLore(list);
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setOwner(String owner) {
        if (itemstack.getItemMeta() instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) itemstack.getItemMeta();

            skullMeta.setOwner(owner);

            itemstack.setItemMeta(skullMeta);
        }

        return this;
    }

    public ItemBuilder addLore(String... lore) {
        ItemMeta meta = itemstack.getItemMeta();

        List<String> list = new ArrayList<String>();
        if (meta.getLore() != null) {
            list.addAll(meta.getLore());
        }

        for (String str : lore)
            list.add(ChatColor.translateAlternateColorCodes('&', str));

        meta.setLore(list);
        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchant, Integer level) {
        if (itemstack.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemstack.getItemMeta();
            meta.addStoredEnchant(enchant, level, true);
            itemstack.setItemMeta(meta);
        } else {
            itemstack.addUnsafeEnchantment(enchant, level);
        }
        return this;
    }

    public void setColor(int rgb) {
        if (itemstack.getType().name().contains("LEATHER_")) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemstack.getItemMeta();

            leatherArmorMeta.setColor(Color.fromRGB(rgb));

            itemstack.setItemMeta(leatherArmorMeta);
        }
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.entrySet().forEach(entry -> System.out.println(entry.getKey()));

        if (itemstack.getType() == Material.ENCHANTED_BOOK) {
            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) itemstack.getItemMeta();
            enchantments.entrySet().forEach(entry -> meta.addStoredEnchant(entry.getKey(), entry.getValue(), true));
            itemstack.setItemMeta(meta);
        } else {
            enchantments.entrySet().forEach(entry -> itemstack.addUnsafeEnchantment(entry.getKey(), entry.getValue()));
        }
        return this;
    }

    public ItemBuilder addFlag(ItemFlag flag) {
        ItemMeta meta = itemstack.getItemMeta();
        meta.addItemFlags(flag);

        itemstack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder clone() {
        return new ItemBuilder(itemstack);
    }

    public ItemStack build() {
        return itemstack.clone();
    }
}