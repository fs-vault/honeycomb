package xyz.nkomarn.honeycomb.interaction;

import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;

/**
 * Represents an object that a player can interact
 * with inside of a {@link Menu}.
 *
 * @since 1.1
 */
public interface Interactive {

    /**
     * Performs an action when this menu is interacted with. An
     * {@link Interaction} object is passed to this method with
     * information about the type of interaction.
     *
     * @param interaction The interaction.
     * @since 1.1
     */
    void handleInteraction(@NotNull Interaction interaction);
}
