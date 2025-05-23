package net.blazinblaze.gpaddons.gppurgeaddon;

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

public class TimedPurgeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1) {
            if (sender instanceof Player) {
                int timeValue;
                try {
                    timeValue = Integer.parseInt(args[0]);
                }
                catch (NumberFormatException e) {
                    sender.sendMessage("§c Invalid time. Resulting to default of 60 seconds.");
                    timeValue = 60;
                }
                Player player = (Player) sender;
                if (GPPurgeAddon.isPurgeActive()) {
                    sender.sendMessage("§c§l A purge is already active!");
                }
                else {
                    GPPurgeAddon.setTimedPurgeActive(true);
                    List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                    for (int i = 0; i < list.size(); i++)
                    {
                        Player plr = list.get(i);
                        GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = true;
                        plr.sendTitle("§c§lTHE PURGE HAS BEGUN.", "§4Protect your claims!", 10, 100, 20);
                        plr.playSound(plr.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0F, 1.0F);
                    }
                    sender.sendMessage("§c§l Purge activated!");
                    PurgeCommandTask commandTask = new PurgeCommandTask();
                    commandTask.runTaskLater(GPPurgeAddon.getInstance(), 20L * timeValue);
                }
            }
        } else if (args.length > 1) {
            sender.sendMessage("§c Please use the following command syntax: /claimpurgetimed <time-in-seconds>");
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (GPPurgeAddon.isPurgeActive()) {
                    sender.sendMessage("§c§l A purge is already active!");
                }
                else {
                    GPPurgeAddon.setTimedPurgeActive(true);
                    List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
                    for (int i = 0; i < list.size(); i++)
                    {
                        Player plr = list.get(i);
                        GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = true;
                        plr.sendTitle("§c§lTHE PURGE HAS BEGUN.", "§4Protect your claims!", 10, 100, 20);
                        plr.playSound(plr.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0F, 1.0F);
                    }
                    sender.sendMessage("§c§l Purge activated!");
                    PurgeCommandTask commandTask = new PurgeCommandTask();
                    commandTask.runTaskLater(GPPurgeAddon.getInstance(), 20L * 60);
                }
            }
        }

        return true;
    }

    private static class PurgeCommandTask extends BukkitRunnable
    {
        @Override
        public void run() {
            GPPurgeAddon.setTimedPurgeActive(false);
            List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i < list.size(); i++) {
                Player plr = list.get(i);
                GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
                plr.sendTitle("§a§lTHE PURGE HAS ENDED!", "§2You may rest.", 10, 100, 20);
                plr.playSound(plr.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
            }
        }
    }
}
