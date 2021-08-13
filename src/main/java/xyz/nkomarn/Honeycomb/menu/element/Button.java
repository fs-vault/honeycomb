package xyz.nkomarn.honeycomb.menu.element;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.interaction.Interaction;
import xyz.nkomarn.honeycomb.interaction.Interactive;

public class Button extends Item implements Interactive {

    private final InteractionHandler handler;

    public Button(int slot, @NotNull ItemStack item, @NotNull InteractionHandler handler) {
        super(slot, item);
        this.handler = handler;
    }

    @Override
    public void handleInteraction(@NotNull Interaction interaction) {
        if (slot() != interaction.slot()) {
            return;
        }

        handler.onInteract(this, interaction);
    }

    @FunctionalInterface
    public interface InteractionHandler {

        void onInteract(@NotNull Button button, @NotNull Interaction interaction);
    }
}
