package games.trident.skills.utilities.menu;

import games.trident.skills.utilities.menu.event.PlayerCloseMenuEvent;
import games.trident.skills.utilities.menu.event.PlayerOpenMenuEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MenuAPI implements Listener {
    private static MenuAPI i = null;
    private static JavaPlugin plugin;

    protected MenuAPI(JavaPlugin plugin) {
    	this.plugin = plugin;
    	
    	Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static MenuAPI getMenuAPI() {
        return i;
    }
    
    public static MenuAPI onEnable(JavaPlugin plugin) {
    	if (i == null)
    		i = new MenuAPI(plugin);
    	
    	return i;
    }

    public Menu createMenu(String title, int rows) {
        return new Menu(title, rows);
    }

    public Menu cloneMenu(Menu menu) {
        return menu.clone();
    }

    public void removeMenu(Menu menu) {
        for (HumanEntity viewer : menu.getInventory().getViewers()) {
            if (viewer instanceof Player)
                menu.closeMenu((Player) viewer);
            else
                viewer.closeInventory();
        }
    }

    public static void openMenu(final Player player, final Menu toMenu) {
        openMenu(player, toMenu, true);
    }
    
    public static void openMenu(final Player player, final Menu toMenu, boolean resetCursor) {
        if(resetCursor)
            player.closeInventory();

        new BukkitRunnable() {
            public void run() {
                PlayerOpenMenuEvent playerOpenMenuEvent = new PlayerOpenMenuEvent(player, toMenu);
                Bukkit.getPluginManager().callEvent(playerOpenMenuEvent);
                toMenu.openMenu(player);
            }
        }.runTask(plugin);
    }

    @EventHandler
    public void onMenuItemDropDragged(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof Menu) {
            Menu menu = (Menu) inventory.getHolder();

            for (int index : event.getRawSlots()) {
                if (!menu.isClickableSlot(index)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMenuItemClicked(final InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        if (inventory.getHolder() instanceof Menu) {
            Menu menu = (Menu) inventory.getHolder();

            if (menu.cancelClicks() && event.getSlotType() == InventoryType.SlotType.CONTAINER && !menu.isClickableSlot(event.getRawSlot())) {
                event.setCancelled(true);

                ((Player) event.getWhoClicked()).updateInventory();

//                switch(event.getAction()) {
//                    case DROP_ALL_CURSOR:
//                    case DROP_ALL_SLOT:
//                    case DROP_ONE_CURSOR:
//                    case DROP_ONE_SLOT:
//                    case PLACE_ALL:
//                    case PLACE_ONE:
//                    case PLACE_SOME:
//                    case COLLECT_TO_CURSOR:
//                    case UNKNOWN:
//                        return;
//                    default:
//                        break;
//                }
            }

            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();

                if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
                    if (menu.exitOnClickOutside())
                        menu.closeMenu(player);
                } else {
                    int index = event.getRawSlot();

                    if (index < inventory.getSize()) {
                        if (event.getAction() != InventoryAction.NOTHING)
                            menu.selectMenuItem(player, index, InventoryClickType.fromInventoryAction(event.getAction()));
                    } else {
                        if (menu.exitOnClickOutside()) {
                            menu.closeMenu(player);
                        } else {
                            if (event.getClickedInventory() instanceof PlayerInventory) {
                                menu.selectPlayerInventoryItem(player, event.getSlot(), InventoryClickType.fromInventoryAction(event.getAction()));
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMenuClosed(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Inventory inventory = event.getInventory();

            if (inventory.getHolder() instanceof Menu) {
                Menu menu = (Menu) inventory.getHolder();
                MenuCloseBehaviour menuCloseBehaviour = menu.getMenuCloseBehaviour();
                if (menuCloseBehaviour != null)
                    menuCloseBehaviour.onClose((Player) event.getPlayer(), menu, menu.bypassMenuCloseBehaviour());

                PlayerCloseMenuEvent playerCloseMenuEvent = new PlayerCloseMenuEvent((Player) event.getPlayer(), menu);
                Bukkit.getPluginManager().callEvent(playerCloseMenuEvent);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerLogoutCloseMenu(PlayerQuitEvent event) {
        if (event.getPlayer().getOpenInventory() == null)
            return;

        InventoryHolder holder = event.getPlayer().getOpenInventory().getTopInventory().getHolder();

        if (holder == null || !(holder instanceof Menu))
            return;

        Menu menu = (Menu)holder;
        menu.setBypassMenuCloseBehaviour(true);
        menu.setMenuCloseBehaviour(null);
        event.getPlayer().closeInventory();
    }

    public interface MenuCloseBehaviour {
        void onClose(Player player, Menu menu, boolean bypassMenuCloseBehaviour);
    }
}