package xyz.nkomarn.honeycomb.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import xyz.nkomarn.honeycomb.interaction.Interactive;
import xyz.nkomarn.honeycomb.menu.element.ContextualElement;
import xyz.nkomarn.honeycomb.menu.element.Element;

import java.util.Collection;

/**
 * Represents a graphical user interface which is backed by a
 * container menu, also known as an inventory. {@link ContextualElement}s
 * can be added to the menu and they will be rendered when the
 * menu is created.
 * <p>
 * Multiple players can view the same menu at a time.
 *
 * @since 1.1
 */
public interface Menu extends Interactive {

    /**
     * Creates a new menu {@link Builder} instance, used to
     * create menus.
     *
     * @return A new menu builder instance.
     * @since 1.1
     */
    @NotNull
    static Builder builder() {
        return new InteractiveMenu.Builder();
    }

    /**
     * Returns the backing {@link Inventory} for this menu.
     *
     * @return The backing Bukkit inventory.
     * @since 1.1
     */
    @NotNull
    Inventory inventory();

    /**
     * Returns a mutable collection of {@link Element}s that are
     * bound to this menu and are applicable for rendering.
     *
     * @return A collection of elements.
     * @since 1.1
     */
    @NotNull
    Collection<Element> elements();

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
    @Unmodifiable
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
}
