package net.blazinblaze.gpaddons.gppurgeaddon;

import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TogglePurgeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(GPPurgeAddon.isTimedPurgeActive()) {
                sender.sendMessage("§c§l A purge is already active!");
            }else {
                if(GPPurgeAddon.isPurgeActive()) {
                    GPPurgeAddon.setPurgeActive(false);
                    List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                    for(int i = 0; i < list.size(); i++) {
                        Player plr = list.get(i);
                        GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
                        Vector<Claim> claims = GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).getClaims();
                        for(int i2 = 0; i2 < claims.size(); i2++) {
                            claims.get(i2).clearPermissions();
                        }
                        plr.sendTitle("§a§lTHE PURGE HAS ENDED!", "§2You may rest.", 10, 100, 20);
                        plr.sendMessage("§cPermissions for your claims have been reset to prevent malicious permission changes during the purge. Please re-add your permissions.");
                        plr.playSound(plr.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
                    }
                    sender.sendMessage("§a§l Purge deactivated!");
                }else {
                    GPPurgeAddon.setPurgeActive(true);
                    List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                    for(int i = 0; i < list.size(); i++) {
                        Player plr = list.get(i);
                        GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = true;
                        plr.sendTitle("§c§lTHE PURGE HAS BEGUN.", "§4Protect your claims!", 10, 100, 20);
                        plr.playSound(plr.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0F, 1.0F);
                    }
                    sender.sendMessage("§c§l Purge activated!");
                }
            }
        }

        return true;
    }
}
