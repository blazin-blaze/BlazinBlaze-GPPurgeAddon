package net.blazinblaze.gpaddons.gppurgeaddon;

import org.bukkit.plugin.java.JavaPlugin;

public class GPPurgeAddon extends JavaPlugin
{
    private static GPPurgeAddon instance;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("GPPurgeAddon is active!");
        getCommand("toggleclaimpurge").setExecutor(new PurgeCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("GPPurgeAddon is inactive!");
        instance = null;
    }

    public static GPPurgeAddon getInstance() {
        return instance;
    }
}

