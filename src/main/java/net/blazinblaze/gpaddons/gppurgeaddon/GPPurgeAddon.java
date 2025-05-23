package net.blazinblaze.gpaddons.gppurgeaddon;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class GPPurgeAddon extends JavaPlugin
{
    private static GPPurgeAddon instance;
    private static boolean purgeActive;
    private static boolean isTimedPurgeActive;

    @Override
    public void onEnable() {
        instance = this;
        purgeActive = false;
        isTimedPurgeActive = false;
        getLogger().info("GPPurgeAddon is active!");
        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(int i = 0; i < list.size(); i++) {
            Player plr = list.get(i);
            GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
        }
        getCommand("claimpurgetoggle").setExecutor(new TogglePurgeCommand());
        getCommand("claimpurgetimed").setExecutor(new TogglePurgeCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("GPPurgeAddon is inactive!");
        instance = null;
        purgeActive = false;
        isTimedPurgeActive = false;
        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(int i = 0; i < list.size(); i++) {
            Player plr = list.get(i);
            GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
        }
    }

    public static GPPurgeAddon getInstance() {
        return instance;
    }

    public static boolean isPurgeActive() {
        return purgeActive;
    }

    public static void setPurgeActive(boolean value) {
        purgeActive = value;
    }

    public static boolean isTimedPurgeActive() {
        return isTimedPurgeActive;
    }

    public static void setTimedPurgeActive(boolean value) {
        isTimedPurgeActive = value;
    }
}

