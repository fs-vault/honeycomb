package xyz.nkomarn.honeycomb.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import xyz.nkomarn.honeycomb.interaction.Interaction;
import xyz.nkomarn.honeycomb.interaction.Interactive;
import xyz.nkomarn.honeycomb.menu.element.ContextualElement;
import xyz.nkomarn.honeycomb.menu.element.Element;
import xyz.nkomarn.honeycomb.util.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A basic {@link Menu} implementation backed by an {@link Inventory}
 * which can be interacted with by viewing players.
 * <p>
 * This implementation should be sufficient for the majority of use
 * cases.
 *
 * @since 1.1
 */
public class InteractiveMenu implements Menu {

    private final Inventory inventory;
    private final List<Element> elements;

    public InteractiveMenu(@NotNull Menu.Builder builder) {
        // TODO holder is wrong at this stage
        this(builder.build().inventory());
    }

    private InteractiveMenu(@NotNull Inventory inventory) {
        this.inventory = inventory;
        this.elements = new ArrayList<>();
    }

    @Override
    @NotNull
    public Inventory inventory() {
        return inventory;
    }

    @Override
    @NotNull
    public Collection<Element> elements() {
        return elements;
    }

    @Override
    public void addElements(@NotNull Element... elements) {
        Arrays.stream(elements).forEach(element -> {
            element.render(this);
            this.elements.add(element);
        });
    }

    @Override
    @NotNull
    public Collection<Player> viewers() {
        return inventory().getViewers().stream()
                .filter(human -> human instanceof Player)
                .map(Player.class::cast)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isViewing(@NotNull Player player) {
        return inventory().getViewers().contains(player);
    }

    @Override
    public void open(@NotNull Player... players) {
        Arrays.stream(players).forEach(this::open0);
    }

    private void open0(@NotNull Player player) {
        player.openInventory(inventory());
    }

    @Override
    public void close(@NotNull Player... players) {

    }

    @Override
    public void close() {
        inventory().getViewers().forEach(HumanEntity::closeInventory);
    }

    @Override
    public void update() {
        elements().stream()
                .filter(Element::shouldRender)
                .forEach(element -> element.render(this));

        viewers().forEach(Player::updateInventory);
    }

    @Override
    public void handleInteraction(@NotNull Interaction interaction) {
        elements().stream()
                .filter(element -> element instanceof Interactive)
                .map(Interactive.class::cast)
                .forEach(element -> element.handleInteraction(interaction));
    }

    public static class Builder implements Menu.Builder {

        private final List<ContextualElement> elements;
        private InventoryType type;
        private String title;
        private int size;

        public Builder() {
            this.elements = new ArrayList<>();
            this.type = InventoryType.CHEST;
            this.size = type.getDefaultSize();
        }

        @Override
        @NotNull
        public Menu.Builder type(@NotNull InventoryType type) {
            this.type = type;
            return this;
        }

        @Override
        @NotNull
        public Menu.Builder title(@NotNull String title) {
            this.title = title;
            return this;
        }

        @Override
        @NotNull
        public Menu.Builder size(int size) {
            this.size = MathUtils.clampInt(size, 0, 54);
            return this;
        }

        @Override
        @NotNull
        public Menu.Builder rows(int rows) {
            this.size = MathUtils.clampInt(rows, 1, 6) * 9;
            return this;
        }

        @Override
        @NotNull
        public Menu.Builder element(@NotNull ContextualElement element) {
            elements.add(element);
            return this;
        }

        @Override
        @NotNull
        public Menu build() {
            var holder = new MenuHolder();
            var inventory = type == InventoryType.CHEST
                    ? Bukkit.createInventory(holder, size, title)
                    : Bukkit.createInventory(holder, type, title);
            var menu = new InteractiveMenu(inventory);

            menu.addElements(elements.toArray(new ContextualElement[0]));
            holder.menu(menu);
            return menu;
        }
    }
}
