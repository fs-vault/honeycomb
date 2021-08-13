package xyz.nkomarn.honeycomb.menu.element;

import com.google.common.base.Preconditions;

public abstract class ContextualElement implements Element {

    private final int slot;

    public ContextualElement(int slot) {
        Preconditions.checkArgument(slot >= 0 && slot <= 54);
        this.slot = slot;
    }

    public int slot() {
        return slot;
    }
}
