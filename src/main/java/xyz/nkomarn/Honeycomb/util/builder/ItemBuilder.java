package xyz.nkomarn.honeycomb.util.builder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder extends AbstractItemBuilder<ItemBuilder> {

    /**
     * Single item
     * @param material The material of the ItemStack
     */
    protected ItemBuilder(Material material) {
        super(material);
    }

    /**
     * Custom amount of item
     * @param material The material of the ItemStack
     * @param amount The amount of the ItemStack
     */
    protected ItemBuilder(Material material, int amount) {
        super(material, amount);
    }

    /**
     * Starting ItemStack
     * @param item The starting ItemStack
     */
    protected ItemBuilder(ItemStack item) {
        super(item);
    }

    /**
     * Starting ItemStack
     * @param item The starting ItemStack
     */
    public static ItemBuilder ofItem(ItemStack item) {
        return new ItemBuilder(item.clone());
    }

    public static ItemBuilder ofType(Material material) {
        return new ItemBuilder(new ItemStack(material));
    }
}
