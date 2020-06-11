package xyz.nkomarn.Honey;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.nkomarn.Honey.handler.GuiHandler;

public class Honey extends JavaPlugin {
    private static Honey honey;

    public void onEnable() {
        honey = this;
        getServer().getPluginManager().registerEvents(new GuiHandler(), this);
    }

    public void onDisable() {
        GuiHandler.closeAll();
    }

    public static Honey getHoney() {
        return honey;
    }
}
