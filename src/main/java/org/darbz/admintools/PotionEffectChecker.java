package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.darbz.admintools.Admintools.prefix;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class PotionEffectChecker implements CommandExecutor {

    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + "PotionCheck" + ChatColor.DARK_GRAY + "] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return false;
        }

        Player player = (Player) sender;
        Player target = args.length == 1 ? Bukkit.getPlayerExact(args[0]) : player;

        if (target == null || !target.isOnline()) {
            player.sendMessage(prefix + ChatColor.RED + "That player is not online.");
            return true;
        }

        player.sendMessage(prefix + ChatColor.GOLD + target.getName() + ChatColor.YELLOW + "'s Active Potion Effects:");
        if (target.getActivePotionEffects().isEmpty()) {
            player.sendMessage(ChatColor.GRAY + "> None");
        } else {
            for (PotionEffect effect : target.getActivePotionEffects()) {
                String effectName = ChatColor.AQUA + effect.getType().getName().replace("_", " ");
                String amplifier = ChatColor.LIGHT_PURPLE + "Level: " + (effect.getAmplifier() + 1);
                String duration = ChatColor.GOLD + "Duration: " + (effect.getDuration() / 20) + "s";
                player.sendMessage(ChatColor.GRAY + "> " + effectName + ChatColor.GRAY + " | " + amplifier + ChatColor.GRAY + " | " + duration);
            }
        }

        player.playSound(player.getLocation(), Sound.BLOCK_BREWING_STAND_BREW, 0.7F, 1.2F);
        return true;
    }
}


