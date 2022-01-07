package games.trident.skills.utilities.menu;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import games.trident.skills.utilities.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Menu implements InventoryHolder {
	private Map<Integer, MenuItem> items = new HashMap<>();
	private Map<Integer, MenuItem> inventorySlots = Maps.newHashMap();
	private Inventory inventory;
	private String title;
	private int rows;
	private boolean exitOnClickOutside = true;
	private MenuAPI.MenuCloseBehaviour menuCloseBehaviour;
	private boolean bypassMenuCloseBehaviour = false;
	private boolean cancelClicks = true;
	private List<Integer> clickableSlots = Lists.newArrayList();

	private ItemStack sideIcon = null;
	private ItemStack inventoryIcon = null;

	private Menu parentMenu;
	private InventoryType type = InventoryType.CHEST;

	public Menu(String title, int rows) {
		this(ChatColor.translateAlternateColorCodes('&', title), rows, null);
	}

	public Menu(String title, int rows, Menu parentMenu) {
		this.title = title;
		this.rows = rows;
		this.parentMenu = parentMenu;
	}

	public void setMenuCloseBehaviour(MenuAPI.MenuCloseBehaviour menuCloseBehaviour) {
		this.menuCloseBehaviour = menuCloseBehaviour;
	}

	public MenuAPI.MenuCloseBehaviour getMenuCloseBehaviour() {
		return menuCloseBehaviour;
	}

	public void setBypassMenuCloseBehaviour(boolean bypassMenuCloseBehaviour) {
		this.bypassMenuCloseBehaviour = bypassMenuCloseBehaviour;
	}

	public boolean cancelClicks() {
		return cancelClicks;
	}

	public void setCancelClicks(boolean cancelClicks) {
		this.cancelClicks = cancelClicks;
	}

	public void addClickableSlots(int... slots) {
		for (int i : slots) clickableSlots.add(i);
	}

	public void removeClickableSlots(int... slots) {
		for (int r : slots) clickableSlots.remove(r);
	}

	public boolean isClickableSlot(int slot) {
		return clickableSlots.contains(slot);
	}

	public ItemStack getSideIcon() {
		return sideIcon;
	}

	public void setSideIcon(ItemStack sideIcon) {
		this.sideIcon = sideIcon;
	}

	public ItemStack getInventoryIcon() {
		return inventoryIcon;
	}

	public void setInventoryIcon(ItemStack inventoryIcon) {
		this.inventoryIcon = inventoryIcon;
	}

	public boolean bypassMenuCloseBehaviour() {
		return bypassMenuCloseBehaviour;
	}

	public void setExitOnClickOutside(boolean exit) {
		this.exitOnClickOutside = exit;
	}

	public Map<Integer, MenuItem> getMenuItems() {
		return items;
	}

	public boolean addMenuItem(MenuItem item, int x, int y) {
		return addMenuItem(item, y * 9 + x);
	}

	public boolean addMenuItem(MenuItem item, int index) {
		ItemStack slot = getInventory().getItem(index);
		if (slot != null && slot.getType() != Material.AIR)
			return false;

		item.setSlot(index);
		getInventory().setItem(index, item.getItemStack());
		items.put(index, item);
		item.addToMenu(this);
		return true;
	}

	public MenuItem getMenuItem(int index) {
		return items.get(index);
	}

	public void setMenuItem(MenuItem item, int index) {
		ItemStack slot = getInventory().getItem(index);

		if (!ItemUtil.isAirOrNull(slot)) {
			getInventory().setItem(index, null);
			items.remove(index).removeFromMenu(this);
		}

		item.setSlot(index);
		getInventory().setItem(index, item.getItemStack());
		items.put(index, item);
		item.addToMenu(this);
	}

	public boolean addPlayerInventorySlot(Player player, MenuItem item, int index) {
		ItemStack slot = player.getInventory().getItem(index);

		if (ItemUtil.isAirOrNull(slot))
			return false;

		item.setSlot(index);
		inventorySlots.put(index, item);
		item.addToMenu(this);

		System.out.println("Added: " + index);
		return true;
	}

	public boolean removePlayerInventorySlot(int index) {
		if (!inventorySlots.containsKey(index))
			return false;

		inventorySlots.remove(index).removeFromMenu(this);
		return true;
	}

	public boolean removeMenuItem(int x, int y) {
		return removeMenuItem(y * 9 + x);
	}

	public boolean removeMenuItem(int index) {
		ItemStack slot = getInventory().getItem(index);

		if (slot == null || slot.getType().equals(Material.AIR))
			return false;
		
		getInventory().clear(index);
		items.remove(index).removeFromMenu(this);
		return true;
	}

	public void fillBoarders(MenuItem item) {
		int size = getInventory().getSize();
		int rows = size / 9;

		if(rows >= 3) {
			for (int i = 0; i <= 8; i++) {
				addMenuItem(item, i);
			}

			for(int s = 8; s < (size - 9); s += 9) {
				addMenuItem(item, s);
				addMenuItem(item, s + 1);
			}

			for (int lr = (size - 9); lr < size; lr++) {
				addMenuItem(item, lr);
			}
		}
	}

	protected void selectMenuItem(Player player, int index, InventoryClickType clickType) {
		if (items.containsKey(index)) {
			MenuItem item = items.get(index);
			item.onClick(player, clickType);
		}
	}

	protected boolean selectPlayerInventoryItem(Player player, int index, InventoryClickType clickType) {
		if (inventorySlots.containsKey(index)) {
			MenuItem item = inventorySlots.get(index);
			item.onClick(player, clickType);
			return true;
		}

		return false;
	}

	public void openMenu(Player player) {
		if (!getInventory().getViewers().contains(player)) {
				player.openInventory(getInventory());
		}
	}

	public void closeMenu(Player player) {
		if (getInventory().getViewers().contains(player)) {
			getInventory().getViewers().remove(player);
			player.closeInventory();
		}
	}

	public Menu getParent() {
		return parentMenu;
	}

    public int getRows() {
        return rows;
    }

    public void setType(InventoryType type) {
		this.type = type;
	}

	public Inventory getInventory() {
		if (inventory == null) {
			if (type == InventoryType.CHEST)
				inventory = Bukkit.createInventory(this, rows * 9, title);
			else
				inventory = Bukkit.createInventory(this, type, title);
		}

		return inventory;
	}

	public boolean exitOnClickOutside() {
		return exitOnClickOutside;
	}

	public void addSpacerItems(MenuItem spacer, int... slots) {
		for (int slot : slots) {
			addMenuItem(spacer, slot);
		}
	}

	@Deprecated
	public void fillSpacers(MenuItem menuItem) {
		for (int i = 0; i < getInventory().getSize(); i++) {
			addMenuItem(menuItem, i);
		}
	}

	protected Menu clone() {
		Menu clone = new Menu(title, rows);
		clone.setExitOnClickOutside(exitOnClickOutside);
		clone.setMenuCloseBehaviour(menuCloseBehaviour);
		for (Entry<Integer, MenuItem> entry : items.entrySet())
			clone.addMenuItem(entry.getValue(), entry.getKey());
		
		return clone;
	}

	public void updateMenu() {
		for (HumanEntity entity : getInventory().getViewers()) ((Player) entity).updateInventory();
	}
}