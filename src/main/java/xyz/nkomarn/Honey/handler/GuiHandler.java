package xyz.nkomarn.Honey.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import xyz.nkomarn.Honey.Honey;
import xyz.nkomarn.Honey.gui.Gui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Listener class that manages GUI clicks and closing,
 * as well as keeps track of all open GUIs.
 */
public class GuiHandler implements Listener {
    private static final Set<Gui> viewingGuis = Collections.synchronizedSet(new HashSet<>());

    @EventHandler(priority = EventPriority.HIGH)
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Gui gui = getCurrentGui(event.getWhoClicked().getUniqueId());
            if (gui != null) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTaskAsynchronously(Honey.getHoney(),
                        () -> gui.handleClick(event));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Gui gui = getCurrentGui(event.getPlayer().getUniqueId());
            if (gui != null) viewingGuis.remove(gui);
        }
    }

    /**
     * Returns the current Gui instance that a specified player is currently viewing.
     * @param uuid The UUID of the player for which to get the viewing Gui Inventory.
     * @return The Gui instance which the player is currently viewing.
     */
    public Gui getCurrentGui(UUID uuid) {
        return viewingGuis.stream()
                .filter(inventory -> inventory.getPlayer().getUniqueId().equals(uuid))
                .findFirst().orElse(null);
    }

    /**
     * Adds a Gui instance to the handler, effectively registering it as a Gui.
     * @param gui The Gui instance to add to the handler.
     */
    public static void registerInventory(Gui gui) {
        viewingGuis.add(gui);
    }

    /**
     * Closes all currently open Gui instances that are registered with this GuiHandler.
     */
    public static void closeAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            for (Gui inventory : viewingGuis) {
                if (inventory.getPlayer().getUniqueId().equals(player.getUniqueId()) && inventory.getInventory()
                        .equals(player.getOpenInventory().getTopInventory())) {
                    player.closeInventory();
                    break;
                }
            }
        }
        viewingGuis.clear();
    }
}
