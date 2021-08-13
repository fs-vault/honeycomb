package xyz.nkomarn.honeycomb.menu.element;

import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.menu.Menu;

public interface Element {

    boolean shouldRender();

    void render(@NotNull Menu menu);
}
