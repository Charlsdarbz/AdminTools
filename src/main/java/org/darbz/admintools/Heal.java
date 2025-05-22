package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.darbz.admintools.Admintools.prefix;

public class Heal implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 1) {
                player.setHealth(20);
                player.setSaturation(20F);
                player.setFoodLevel(20);
                player.sendMessage(ChatColor.RED + "You have been healed!");
                player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 0.5F, 1F);
                return true;
            }


            Player target = Bukkit.getServer().getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + ChatColor.RED + " This player is not online");
                return false;
            }
            target.setHealth(20);
            target.setSaturation(20F);
            target.setFoodLevel(20);
            target.sendMessage(ChatColor.RED + "You have been healed!");
            target.playSound(target.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 0.5F, 1F);
            player.sendMessage(prefix + ChatColor.GRAY + "You Have Healed " + target.getName());
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Only players can use this command.");
        return false;
    }
}
