package xyz.nkomarn.honeycomb;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.interaction.Interaction;
import xyz.nkomarn.honeycomb.menu.Menu;
import xyz.nkomarn.honeycomb.menu.MenuHolder;

public record Honeycomb(JavaPlugin plugin) implements Listener {

    public Honeycomb(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static void initialize() {
        new Honeycomb(JavaPlugin.getProvidingPlugin(Honeycomb.class));
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        var holder = event.getInventory().getHolder();

        if (!(holder instanceof MenuHolder)) {
            return;
        }

        var menu = ((MenuHolder) holder).menu();
        var interaction = new Interaction(
                menu,
                (Player) event.getWhoClicked(),
                event.getRawSlot(),
                event.getClick(),
                event.getAction(),
                event.getSlotType(),
                true
        );

        menu.handleInteraction(interaction);
        event.setCancelled(interaction.shouldCancel());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDisable(PluginDisableEvent event) {
        if (!event.getPlugin().equals(plugin)) {
            return;
        }

        Bukkit.getOnlinePlayers().stream()
                .map(Player::getOpenInventory)
                .map(InventoryView::getTopInventory)
                .map(Inventory::getHolder)
                .filter(MenuHolder.class::isInstance)
                .map(MenuHolder.class::cast)
                .map(MenuHolder::menu)
                .forEach(Menu::close);
    }
}
