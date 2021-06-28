package xyz.nkomarn.honeycomb.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.interaction.Interactive;
import xyz.nkomarn.honeycomb.menu.element.Element;

import java.util.Collection;

/**
 * Represents a graphical user interface which is backed by a
 * container menu, also known as an inventory. {@link Element}s
 * can be added to the menu and they will be rendered when the
 * menu is created.
 * <p>
 * Multiple players can view the same menu at a time.
 *
 * @since 1.1
 */
public interface Menu extends Interactive {

    /**
     * Returns the backing {@link Inventory} for this menu.
     *
     * @return The backing Bukkit inventory.
     * @since 1.1
     */
    @NotNull
    Inventory inventory();

    /**
     * Renders an array of {@link Element}s on top of this
     * menu, directly affecting the backing inventory.
     *
     * @param elements Vararg of elements to render.
     * @since 1.1
     */
    void addElements(@NotNull Element... elements);

    /**
     * Returns an immutable collection of players that are
     * currently viewing this menu.
     *
     * @return A collection of viewers.
     * @since 1.1
     */
    @NotNull
    Collection<Player> viewers();

    /**
     * Returns whether a given player is currently viewing
     * the backing inventory of this menu.
     *
     * @param player The player to check.
     * @return Whether the given player is viewing this menu.
     * @since 1.1
     */
    boolean isViewing(@NotNull Player player);

    /**
     * Displays the rendered backing inventory to the given
     * array of players.
     *
     * @param players Vararg of players to display to.
     * @since 1.1
     */
    void open(@NotNull Player... players);

    /**
     * Forcibly closes the menu for the given array of players.
     * If a player is not currently viewing the menu, the
     * player will be silently skipped.
     *
     * @param players Vararg of players to close for.
     * @since 1.1
     */
    void close(@NotNull Player... players);

    /**
     * Forcibly closes the menu for all viewing players.
     *
     * @since 1.1
     */
    void close();

    /**
     * Sends an inventory update packet for the backing inventory.
     * This resends the full inventory.
     *
     * @since 1.1
     */
    void update();

    /**
     * A builder for menus, intended to make creation of menus
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
         * Adds an {@link Element} to this menu, setting it to be rendered
         * when the backing inventory is created.
         *
         * @param element The element to render.
         * @return This builder instance.
         * @since 1.1
         */
        @NotNull
        Builder element(@NotNull Element element);

        /**
         * Creates a new {@link Menu} instance based on parameters
         * used in this builder.
         *
         * @return A new menu instance with specified parameters.
         * @since 1.1
         */
        @NotNull
        Menu build();
    }
}
