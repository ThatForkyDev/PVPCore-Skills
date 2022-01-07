package games.trident.skills.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class SkillLevelupEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    @Getter private String type;
    @Getter private int level;

    public SkillLevelupEvent(Player player, String type, int level) {
        super(player);

        this.level = level;
        this.type = type;
    }

    @Override
    public final HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
