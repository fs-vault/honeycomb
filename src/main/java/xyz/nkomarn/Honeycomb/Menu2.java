package xyz.nkomarn.honeycomb;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import xyz.nkomarn.honeycomb.menu.Menu;
import xyz.nkomarn.honeycomb.menu.element.Button;
import xyz.nkomarn.honeycomb.util.builder.ItemBuilder;

public class Menu2 {

    public void create() {
        var menu = Menu.builder()
                .title("Banana")
                .rows(5)
                .build();

        menu.open(Bukkit.getOnlinePlayers().toArray(new Player[0]));

        var item = ItemBuilder.ofType(Material.HONEYCOMB)
                .name("&6&lHoneycomb")
                .lore("Banananananan")
                .enchant(Enchantment.MENDING,1 )
                .addItemFlags(ItemFlag.HIDE_ENCHANTS)
                .build();

        menu.addElements(new Button(4, item, (button, interaction) -> {
            item.setType(Material.HONEY_BLOCK);
            button.updateItem(item);
        }));

        menu.update();
    }
}
