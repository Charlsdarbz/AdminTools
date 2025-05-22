package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static org.darbz.admintools.Admintools.prefix;

public class pvptoggle implements CommandExecutor {

    public static boolean Enabled = true;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Enabled) {
                Enabled = false;
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(prefix + ChatColor.RED + "§lPVP HAS BEEN DISABLED!");
                }
            } else {
                Enabled = true;
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(prefix + ChatColor.GREEN + "§lPVP HAS BEEN ENABLED!");
                }
            }

        }
        return false;
    }
}


