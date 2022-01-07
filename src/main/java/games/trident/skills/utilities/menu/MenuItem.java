package games.trident.skills.utilities.menu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private Menu menu;
    private int slot;
    private int number;
    private ItemStack icon;
    private String text = null;
    private List<String> descriptions = new ArrayList<String>();

    public MenuItem(ItemStack icon) {
        this(null, icon);
    }

    public MenuItem(String text, ItemStack icon) {
        this(text, icon, 1);
    }

    public MenuItem(String text, ItemStack icon, int number) {
        if(text != null)
            this.text = ChatColor.translateAlternateColorCodes('&', text);
        
        this.icon = icon;
        this.number = number;
    }

    protected void addToMenu(Menu menu) {
    	this.menu = menu;
    }

    protected void removeFromMenu(Menu menu) {
        if (this.menu == menu) {
        	this.menu = null;
        }
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getSlot() {
        return this.slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getNumber() {
        return this.number;
    }

    public ItemStack getIcon() {
        return this.icon;
    }

    public void setIcon(ItemStack icon) {
    	this.icon = icon;
        this.menu.getInventory().setItem(this.slot, getIcon());
    }

    public String getText() {
        return this.text;
    }

    public void setDescriptions(List<String> lines) {
    	this.descriptions = lines;
    }

    public void addDescription(String line) {
    	this.descriptions.add(ChatColor.translateAlternateColorCodes('&', line));
    }

    protected ItemStack getItemStack() {
        ItemStack slot = getIcon().clone();
        ItemMeta meta = slot.getItemMeta();

        if(meta.hasLore())
            meta.getLore().addAll(this.descriptions);
        else
            meta.setLore(this.descriptions);

        if(getText() != null)
            meta.setDisplayName(getText());

        slot.setItemMeta(meta);
        return slot;
    }

    public void onClick(Player player, InventoryClickType clickType) {}
}