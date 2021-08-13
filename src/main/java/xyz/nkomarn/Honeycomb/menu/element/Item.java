package xyz.nkomarn.honeycomb.menu.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;

public class Item extends ContextualElement {

    private ItemStack item;
    private boolean shouldRender;

    public Item(int slot, @NotNull ItemStack item) {
        super(slot);
        updateItem(item);
    }

    @NotNull
    public ItemStack item() {
        return item;
    }

    public void updateItem(@NotNull ItemStack item) {
        this.item = item;
        this.shouldRender = true;
    }

    @Override
    public boolean shouldRender() {
        return shouldRender;
    }

    @Override
    public void render(@NotNull Menu menu) {
        menu.inventory().setItem(slot(), item());
        this.shouldRender = false;
    }
}
