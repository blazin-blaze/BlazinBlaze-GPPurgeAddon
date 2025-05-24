package net.blazinblaze.gpaddons.gppurgeaddon;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GPPurgeAddon extends JavaPlugin {
    private static GPPurgeAddon instance;
    private static boolean purgeActive;
    private static boolean isTimedPurgeActive;
    private static boolean shouldResetPerms;

    @Override
    public void onEnable() {
        instance = this;
        purgeActive = false;
        isTimedPurgeActive = false;
        saveDefaultConfig();
        shouldResetPerms = getConfig().getBoolean("should-reset-perms");
        getCommand("claimpurgetoggle").setExecutor(new TogglePurgeCommand());
        getCommand("claimpurgetimed").setExecutor(new TimedPurgeCommand());
        getLogger().info("GPPurgeAddon is active!");
    }

    @Override
    public void onDisable() {
        instance = null;
        if(purgeActive || isTimedPurgeActive) {
            List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i < list.size(); i++) {
                Player plr = list.get(i);
                GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
                if(shouldResetPerms) {
                    Vector<Claim> claims = GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).getClaims();
                    for(int i2 = 0; i2 < claims.size(); i2++) {
                        claims.get(i2).clearPermissions();
                    }
                    plr.sendMessage("§cPermissions for your claims have been reset to prevent malicious permission changes during the purge. Please re-add your permissions.");
                }
            }
        }
        purgeActive = false;
        isTimedPurgeActive = false;
        getLogger().info("GPPurgeAddon is inactive!");
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

    public static boolean getShouldResetPerms() {
        return shouldResetPerms;
    }
}

