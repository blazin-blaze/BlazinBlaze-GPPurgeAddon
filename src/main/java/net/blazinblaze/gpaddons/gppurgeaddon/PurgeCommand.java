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

public class PurgeCommand implements CommandExecutor {
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i < list.size(); i++) {
                Player plr = list.get(i);
                GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = true;
                plr.sendTitle("§cTHE PURGE HAS BEGUN.", "§4Protect your claims!", 10, 100, 20);
                plr.playSound(plr.getLocation(), Sound.ENTITY_WITHER_DEATH, 10.0F, 1.0F);
            }
            PurgeCommandTask commandTask = new PurgeCommandTask();
            commandTask.runTaskLater(GPPurgeAddon.getInstance(), 20*60);
        }

        return true;
    }

    private static class PurgeCommandTask extends BukkitRunnable {
        @Override
        public void run() {
            List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i < list.size(); i++) {
                Player plr = list.get(i);
                GriefPrevention.instance.dataStore.getPlayerData(plr.getUniqueId()).ignoreClaims = false;
                plr.sendTitle("§aTHE PURGE HAS ENDED!", "§2You may rest.", 10, 100, 20);
                plr.playSound(plr.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
            }
        }
    }
}
