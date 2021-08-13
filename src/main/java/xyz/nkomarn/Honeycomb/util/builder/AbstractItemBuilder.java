package xyz.nkomarn.honeycomb.util.builder;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

// TODO extract out item builders into a separate library

/**
 * A Builder class for Bukkit {@link ItemStack}s. This base builder
 * class provides basic ItemStack manipulation methods. Other item-
 * specific builders extend this base to add extra methods.
 * <p>
 * A new builder can be created using the {@link ItemBuilder#ofType(Material)}
 * method, or an existing ItemStack can be converted into a builder
 * using {@link ItemBuilder#ofItem(ItemStack)}.
 * <p>
 * ItemBuilders greatly accelerate development by speeding up creation
 * and manipulation of items.
 *
 * @see ItemStack
 * @since 1.1
 */
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T>> {

    protected final ItemStack item;

    protected AbstractItemBuilder(@NotNull Material material) {
        this(material, 1);
    }

    protected AbstractItemBuilder(@NotNull Material material, int amount) {
        this(new ItemStack(material, amount));
    }

    protected AbstractItemBuilder(@NotNull ItemStack item) {
        this.item = item;
    }

    @NotNull
    public AbstractItemBuilder<?> base() {
        return this;
    }

    @NotNull
    public ItemStack item() {
        return item;
    }

    @NotNull
    public T material(Material material) {
        item().setType(material);
        return (T) this;
    }

    @NotNull
    public T amount(int amount) {
        item().setAmount(amount);
        return (T) this;
    }

    @NotNull
    public T name(@NotNull String displayName) {
        var meta = item().getItemMeta();
        meta.setDisplayName(displayName);
        item().setItemMeta(meta);
        return (T) this;
    }

    @NotNull
    public T lore(@NotNull String... lore) {
        return lore(Arrays.asList(lore));
    }

    @NotNull
    public T lore(@NotNull List<String> lore) {
        var meta = item().getItemMeta();
        meta.setLore(lore);
        item().setItemMeta(meta);
        return (T) this;
    }

    public T addLore(@NotNull String... lore) {
        return addLore(Arrays.asList(lore));
    }

    public T addLore(@NotNull List<String> lore) {
        ItemMeta meta = item().getItemMeta();

        var newLore = meta.getLore();

        if (newLore == null) {
            newLore = new ArrayList<>();
        }

        newLore.addAll(lore);
        meta.setLore(newLore);
        item().setItemMeta(meta);
        return (T) this;
    }

    public T removeLoreLine(int line) {
        var meta = item().getItemMeta();
        List<String> newLore = meta.getLore();
        if (newLore == null) {
            newLore = new ArrayList<>();
        }

        newLore.remove(line - 1);

        meta.setLore(newLore);
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Set the damage of the ItemStack
     *
     * @param damage The new damage of the ItemStack
     * @return This instance
     */
    public T damage(int damage) {
        Damageable meta = (Damageable) this.item.getItemMeta();
        meta.setDamage(damage);
        this.item.setItemMeta((ItemMeta) meta);
        return (T) this;
    }

    /**
     * Add an enchantment to the ItemStack.
     *
     * @param enchantment The enchantment type to add
     * @param level       The level of the enchantment
     * @return This instance
     */
    public T enchant(Enchantment enchantment, int level) {
        this.item.addEnchantment(enchantment, level);
        return (T) this;
    }

    /**
     * Add an unsafe enchantment to the ItemStack.
     *
     * @param enchantment The enchantment type to add
     * @param level       The level of the enchantment
     * @return This instance
     */
    public T enchantUnsafe(Enchantment enchantment, int level) {
        this.item.addUnsafeEnchantment(enchantment, level);
        return (T) this;
    }

    /**
     * Add an stored enchantment to the ItemStack.
     * Should only be used for enchanted books.
     *
     * @param enchantment The enchantment to add
     * @param level       The level of the enchantment to add.
     * @return This instance
     */
    public T storeEnchantment(Enchantment enchantment, int level) {
        ItemMeta meta = this.item.getItemMeta();
        if (meta instanceof EnchantmentStorageMeta) {
            ((EnchantmentStorageMeta) meta).addStoredEnchant(enchantment, level, true);
        }
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Removes an enchantment from the ItemStack.
     *
     * @param enchantment The enchantment type to remove
     * @return This instance
     */
    public T removeEnchant(Enchantment enchantment) {
        this.item.removeEnchantment(enchantment);
        return (T) this;
    }

    /**
     * Add all ItemFlags's to the ItemStack.
     *
     * @return This instance
     */
    public T addAllItemFlags() {
        return addItemFlags(ItemFlag.values());
    }

    /**
     * Add ItemFlag's to the ItemStack.
     *
     * @param flags The flags to add
     * @return This instance
     */
    public T addItemFlags(ItemFlag... flags) {
        this.item.addItemFlags(flags);
        return (T) this;
    }

    /**
     * Add persistent data to the ItemStack.
     *
     * @param key   The NamespacedKey to persist the data under
     * @param type  The type of the data to persist
     * @param value The data to persist
     * @return This instance
     */
    public <Y, Z> T persistData(NamespacedKey key, PersistentDataType<Y, Z> type, Z value) {
        ItemMeta meta = this.item.getItemMeta();
        meta.getPersistentDataContainer().set(key, type, value);
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Add an attribute modifier to the ItemStack.
     *
     * @param attribute The attribute type to add
     * @param name      The name for the modifier
     * @param amount    The amount for the modifier
     * @param operation The operator to use when applying the modifier
     * @return This instance
     */
    public T attributeModifier(Attribute attribute, String name, Double amount, AttributeModifier.Operation operation) {
        ItemMeta meta = this.item.getItemMeta();
        meta.addAttributeModifier(attribute, new AttributeModifier(name, amount, operation));
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Add an attribute modifier to the ItemStack.
     *
     * @param attribute The attribute type to add
     * @param uuid      The unique id for the modifier
     * @param name      The name for the modifier
     * @param amount    The amount for the modifier
     * @param operation The operator to use when applying the modifier
     * @return This instance
     */
    public T attributeModifier(Attribute attribute, UUID uuid, String name, Double amount, AttributeModifier.Operation operation) {
        ItemMeta meta = this.item.getItemMeta();
        meta.addAttributeModifier(attribute, new AttributeModifier(uuid, name, amount, operation));
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Set customer model data
     *
     * @param integer Customer model data
     * @return This instance
     */
    public T modelData(Integer integer) {
        ItemMeta meta = this.item.getItemMeta();
        meta.setCustomModelData(integer);
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Make the ItemStack unbreakable.
     *
     * @return This instance
     */
    public T unbreakable() {
        ItemMeta meta = this.item.getItemMeta();
        meta.setUnbreakable(true);
        this.item.setItemMeta(meta);
        return (T) this;
    }

    /**
     * Build the ItemStack.
     *
     * @return The build ItemStack
     */
    public ItemStack build() {
        return this.item;
    }
}
