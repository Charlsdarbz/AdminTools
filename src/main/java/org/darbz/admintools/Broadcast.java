package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 1) {
                String message = String.join(" ", args);
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.sendMessage(Admintools.getPrefix() + " " + message);
                }
            } else {
                player.sendMessage(Admintools.getPrefix() + ChatColor.RED + " Please use /broadcast <message>");
            }
        } else {
            sender.sendMessage(Admintools.getPrefix() + ChatColor.RED + " Only players can run this command.");
        }
        return true;
    }
}
