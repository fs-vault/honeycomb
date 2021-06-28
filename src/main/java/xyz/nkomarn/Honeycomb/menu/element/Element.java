package xyz.nkomarn.honeycomb.menu.element;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;

public abstract class Element {

    private final int slot;

    public Element(int slot) {
        Preconditions.checkArgument(slot >= 0 && slot <= 54);
        this.slot = slot;
    }

    public int slot() {
        return slot;
    }

    public abstract void render(@NotNull Menu menu);
}
