package xyz.nkomarn.Honey;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.nkomarn.Honey.handler.GuiHandler;

public class Honeycomb extends JavaPlugin {
    private static Honeycomb honeycomb;

    public void onEnable() {
        honeycomb = this;
        getServer().getPluginManager().registerEvents(new GuiHandler(), this);
    }

    public void onDisable() {
        GuiHandler.closeAll();
    }

    public static Honeycomb getHoneycomb() {
        return honeycomb;
    }
}
