package xyz.nkomarn.Honeycomb.handler;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import xyz.nkomarn.Honeycomb.Honeycomb;
import xyz.nkomarn.Honeycomb.menu.Menu;

import java.util.*;

/**
 * Listener class that manages GUI clicks and closing,
 * as well as keeps track of all open GUIs.
 */
public class MenuHandler implements Listener {
    private static final Map<Inventory, Menu> VIEWING_MENUS = Collections.synchronizedMap(new HashMap<>());

    @EventHandler(priority = EventPriority.HIGH)
    public void onMenuClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Menu menu = VIEWING_MENUS.get(event.getWhoClicked().getOpenInventory().getTopInventory());
            if (menu != null) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTaskAsynchronously(Honeycomb.getHoneycomb(), () -> menu.handleClick(event));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onMenuClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Menu menu = VIEWING_MENUS.get(event.getInventory());
            if (menu != null) {
                VIEWING_MENUS.remove(event.getInventory());
            }
        }
    }

    /**
     * Adds a Gui instance to the handler, effectively registering it as a Gui.
     *
     * @param menu The Gui instance to add to the handler.
     */
    public static void registerMenu(Menu menu) {
        VIEWING_MENUS.put(menu.getInventory(), menu);
    }

    /**
     * Closes all currently open Gui instances that are registered with this GuiHandler.
     */
    public static void closeAll() {
        Bukkit.getOnlinePlayers().forEach(player -> VIEWING_MENUS.entrySet().stream()
                .filter(entry -> entry.getValue().getPlayer().getUniqueId().equals(player.getUniqueId()))
                .filter(entry -> entry.getKey().equals(player.getOpenInventory().getTopInventory()))
                .forEach(entry -> player.closeInventory()));
        VIEWING_MENUS.clear();
    }
}
