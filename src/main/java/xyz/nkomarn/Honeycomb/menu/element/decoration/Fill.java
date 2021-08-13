package xyz.nkomarn.honeycomb.menu.element.decoration;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;
import xyz.nkomarn.honeycomb.menu.element.Element;
import xyz.nkomarn.honeycomb.util.builder.ItemBuilder;

public class Fill implements Element {

    private ItemStack item;
    private boolean shouldRender;

    public Fill(@NotNull Material material) {
        this(ItemBuilder.ofType(material).name(" ").build());
    }

    public Fill(@NotNull ItemStack item) {
        this.item = item;
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
        var inventory = menu.inventory();

        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, item());
        }

        shouldRender = false;
    }
}
