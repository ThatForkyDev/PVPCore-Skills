package games.trident.skills.utilities.menu;

import org.bukkit.event.inventory.InventoryAction;

public enum InventoryClickType {
    LEFT (true, false),
    SHIFT_LEFT (true, true),
    RIGHT (false, false),
    OTHER (false, false);

    private boolean leftClick;
    private boolean shiftClick;

    InventoryClickType(boolean leftClick, boolean shiftClick) {
        this.leftClick = leftClick;
        this.shiftClick = shiftClick;
    }

    public boolean isLeftClick() {
        return this.leftClick && this != OTHER;
    }

    public boolean isRightClick() {
        return !this.leftClick && this != OTHER;
    }

    public boolean isShiftClick() {
        return this.shiftClick;
    }

    public static InventoryClickType fromInventoryAction(InventoryAction action) {
        switch(action) {
            case PICKUP_ALL:
            case PLACE_SOME:
            case PLACE_ALL:
            case SWAP_WITH_CURSOR:
                return LEFT;

            case PICKUP_HALF:
            case PLACE_ONE:
                return RIGHT;

            case MOVE_TO_OTHER_INVENTORY:
                return SHIFT_LEFT;

            default:
                return OTHER;
        }
    }
}