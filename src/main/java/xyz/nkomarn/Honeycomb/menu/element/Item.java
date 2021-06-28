package xyz.nkomarn.honeycomb.menu.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;

public class Item extends Element {

    private final ItemStack item;

    public Item(int slot, @NotNull ItemStack item) {
        super(slot);
        this.item = item;
    }

    @NotNull
    public ItemStack item() {
        return item;
    }

    @Override
    public void render(@NotNull Menu menu) {
        menu.inventory().setItem(slot(), item());
    }
}
