![Honeycomb](https://i.imgur.com/pzwB04b.png)
### A dead simple and easy to use Spigot menu library.
[![License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/nkomarn/Honeycomb/blob/master/LICENSE) [![Discord](https://discordapp.com/api/guilds/285623631042707457/widget.png?style=shield)](https://discord.gg/UACRzwe)

Honeycomb was designed to be as easy to use as possible, allowing easy creation of menus that doesn't involve hacky shit with listeners and consistently works. Creating and opening a menu is as simple as this:

### 🚩 Creating + Opening a Menu
![GUI Example](https://i.imgur.com/ULpJpUR.png)

Here's the code used to make that menu:
```java
// Define the menu and add borders and fills.
Menu menu = new Menu(player, "Honeycomb Menu", 27);
menu.fill(Material.WHITE_STAINED_GLASS_PANE);
menu.fillBorderAlternating(
    Material.PINK_STAINED_GLASS_PANE,
    Material.MAGENTA_STAINED_GLASS_PANE);

// This is the ItemStack we'll use to represent the button.
ItemStack buttonItem = new ItemStack(Material.HONEYCOMB);
ItemMeta buttonMeta = buttonItem.getItemMeta();
buttonMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lHoneycomb"));
buttonMeta.setLore(Arrays.asList(
    ChatColor.GRAY + "A simple menu button.",
    ChatColor.YELLOW + "Click me!"
));
buttonItem.setItemMeta(buttonMeta);

// Add a clickable button to the menu.
menu.addButton(new MenuButton(menu, buttonItem, 13, (button, clickType) -> {
    // Stuff in here runs when the button is pressed.
    // Use ClickType to check for left-, right-, and shift-click.
    // button.setMaterial(Material.HONEY_BOTTLE);
    menu.getPlayer().playSound(menu.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1.0f, 1.f);
    openOtherMenu(player);
}));

// Finally, open the menu to the player.
menu.open();
```

### 🔨 Using the Library
Don't shade Honeycomb into your plugin- instead, add it as a dependency in your `plugin.yml` and install it on your server. Latest builds are available over at the [releases page](https://github.com/nkomarn/Honeycomb/releases).
```yaml
depend: ['Honeycomb']
```

You can directly chuck Honeycomb into Maven using the latest hosted version on [JitPack](https://jitpack.io/#nkomarn/Honeycomb).

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.nkomarn</groupId>
    <artifactId>Honeycomb</artifactId>
    <version>Version tag from JitPack</version>
    <scope>provided</scope>
</dependency>
```

### 🙌 It's as easy as that.
Creating GUI menus has never been simpler. All registration and handling is automatically taken care of for you, allowing you to focus on realizing your idea rather than figuring out how to do it- and that's the philosophy behind Honeycomb.

Honeycomb is a standalone version of the GUI library from [Kerosene](https://github.com/firestarter/kerosene). I always struggled with clunky GUI solutions when making plugins for [Firestarter](https://github.com/firestarter), and after stumbling across the GUI system of [PlayerParticles](https://github.com/Esophose/PlayerParticles), I was heavily inspired to create this system. I hope you enjoy using it as much as I do. ✨