package xyz.nkomarn.honeycomb;

import xyz.nkomarn.honeycomb.menu.InteractiveMenu;
import xyz.nkomarn.honeycomb.menu.Menu;

public class TestMenu extends InteractiveMenu {

    public TestMenu() {
        super(Menu.builder().title("Banana").rows(6));

        update();
    }


}
