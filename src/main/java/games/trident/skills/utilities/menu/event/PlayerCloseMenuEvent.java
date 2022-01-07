package games.trident.skills.utilities.menu.event;

import games.trident.skills.utilities.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerCloseMenuEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Menu menu;

    public PlayerCloseMenuEvent(Player player, Menu menu) {
        super(player);

        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
