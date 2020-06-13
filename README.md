![Honeycomb](https://i.imgur.com/pzwB04b.png)
### A dead simple and easy to use Spigot GUI library.
[![License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/nkomarn/Honeycomb/blob/master/LICENSE) [![Discord](https://discordapp.com/api/guilds/645808053001781279/widget.png?style=shield)](https://discord.gg/wPNY8Yk)

Honeycomb was designed to be as easy to use as possible, allowing easy creation of GUIs that doesn't involve hacky shit with listeners and consistently works. Creating and opening a GUI is as simple as this:

### 🚩 Creating + Opening a GUI
![GUI Example](https://i.imgur.com/JOT3uBg.png)

Here's the code used to make that GUI:
```java
// Define the GUI and add borders and fills.
Gui gui = new Gui(player, "Honey GUI", 27);
gui.fill(Material.WHITE_STAINED_GLASS_PANE);
gui.fillBorderAlternating(Material.ORANGE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE);

// This is the ItemStack we'll use to represent the button.
ItemStack honeyButton = new ItemStack(Material.HONEY_BLOCK);
ItemMeta honeyMeta = honeyButton.getItemMeta();
honeyMeta.setDisplayName(ChatColor.GOLD + "Honey");
honeyMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Click me!"));
honeyButton.setItemMeta(honeyMeta);

// Add a clickable button to the GUI.
gui.addButton(new GuiButton(gui, honeyButton, 13, (button, clickType) -> {
    // Stuff in here runs when the button is pressed.
    // Use ClickType to check for left-, right-, and shift-click.
    button.setType(Material.HONEYCOMB_BLOCK);
    gui.getPlayer().playSound(gui.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
}));

// Finally, open the GUI to the player.
gui.open();
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
Creating GUIs has never been simpler. All registration and handling is automatically taken care of for you, allowing you to focus on realizing your idea rather than figuring out how to do it- and that's the philosophy behind Honeycomb.

Honeycomb is a standalone version of the GUI library from [Kerosene](https://github.com/firestarter/kerosene). I always struggled with clunky GUI solutions when making plugins for [Firestarter](https://github.com/firestarter), and after stumbling across the GUI system of [PlayerParticles](https://github.com/Esophose/PlayerParticles), I was heavily inspired to create this system. I hope you enjoy using it as much as I do. ✨