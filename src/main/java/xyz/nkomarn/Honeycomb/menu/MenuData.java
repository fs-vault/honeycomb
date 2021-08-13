package xyz.nkomarn.honeycomb.menu;

import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.element.ContextualElement;

public interface MenuData {

    /**
     * A builder for menu data, intended to make creation of menus
     * easier through variable options rather than a large
     * selection of constructors.
     *
     * @since 1.1
     */
    interface Builder {

        /**
         * Sets the {@link InventoryType} of the backing inventory.
         *
         * @param type The inventory container type.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder type(@NotNull InventoryType type);

        /**
         * Sets the text displayed at the top of the menu when
         * a player is viewing it.
         *
         * @param title The text to display.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder title(@NotNull String title);

        /**
         * Sets the amount of slots contained in the backing
         * inventory for this menu. This number must be between
         * zero and fifty-four.
         * <p>
         * For {@link InventoryType}s other than {@link InventoryType#CHEST},
         * this value is ignored and instead is automatically populated
         * using {@link InventoryType#getDefaultSize()}.
         *
         * @param size The amount of slots to use.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder size(int size);

        /**
         * Sets the amount of rows of slots contained in the backing
         * inventory for this menu. This number must be between one
         * and six.
         * <p>
         * This is a simpler way to set the size of the inventory
         * by specifying rows rather than individual slot counts.
         * <p>
         * For {@link InventoryType}s other than {@link InventoryType#CHEST},
         * this value is ignored and instead is automatically populated
         * using {@link InventoryType#getDefaultSize()}.
         *
         * @param rows The amount of rows to use.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder rows(int rows);

        /**
         * Adds an {@link ContextualElement} to this menu, setting it to be rendered
         * when the backing inventory is created.
         *
         * @param element The element to render.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder element(@NotNull ContextualElement element);

        /**
         * Creates a new {@link Menu} instance based on parameters
         * used in this builder.
         *
         * @return A new menu instance with specified parameters.
         * @since 1.1
         */
        @NotNull
        Menu buildMenu();
    }
}
