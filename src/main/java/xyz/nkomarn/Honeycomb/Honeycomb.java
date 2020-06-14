package xyz.nkomarn.Honeycomb;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.nkomarn.Honeycomb.handler.MenuHandler;

public class Honeycomb extends JavaPlugin {
    private static Honeycomb honeycomb;

    public void onEnable() {
        honeycomb = this;
        getServer().getPluginManager().registerEvents(new MenuHandler(), this);
    }

    public void onDisable() {
        MenuHandler.closeAll();
    }

    public static Honeycomb getHoneycomb() {
        return honeycomb;
    }
}
