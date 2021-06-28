package xyz.nkomarn.honeycomb.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MenuHolder implements InventoryHolder {

    private Menu menu;

    @NotNull
    public Menu menu() {
        return menu;
    }

    public void menu(@NotNull Menu menu) {
        this.menu = menu;
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return menu.inventory();
    }
}
