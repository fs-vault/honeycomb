package xyz.nkomarn.honeycomb.interaction;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;
import xyz.nkomarn.honeycomb.menu.element.Element;

/**
 * Represents information on a specific player
 * interaction with a {@link Menu} or an individual
 * menu {@link Element}.
 * <p>
 * This interaction is cancelable.
 *
 * @since 1.1
 */
public record Interaction(@NotNull Menu menu, @NotNull Player player, int slot, @NotNull ClickType clickType,
                          @NotNull InventoryAction inventoryAction, @NotNull InventoryType.SlotType slotType,
                          boolean shouldCancel) {
}
