package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class TempBan implements CommandExecutor {

    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "§lBan" + ChatColor.DARK_GRAY + "] ";
    private final HashMap<UUID, Long> tempBans = new HashMap<>();
    private final Plugin plugin;

    public TempBan(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("admin.tempban")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.RED + "Usage: /tempban <player> <durationInSeconds>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage(prefix + ChatColor.RED + "That player is not online.");
            return true;
        }

        long duration;
        try {
            duration = Long.parseLong(args[1]) * 1000L;
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid duration. Use numbers (seconds).");
            return true;
        }

        long unbanTime = System.currentTimeMillis() + duration;
        tempBans.put(target.getUniqueId(), unbanTime);
        target.kickPlayer(prefix + ChatColor.RED + "You have been temp-banned for " + (duration / 1000) + " seconds.");

        sender.sendMessage(prefix + ChatColor.YELLOW + "Temp-banned " + target.getName() + " for " + (duration / 1000) + " seconds.");
        return true;
    }

    public boolean isBanned(Player player) {
        UUID uuid = player.getUniqueId();
        if (tempBans.containsKey(uuid)) {
            long unbanTime = tempBans.get(uuid);
            if (System.currentTimeMillis() < unbanTime) {
                long remaining = (unbanTime - System.currentTimeMillis()) / 1000;
                player.kickPlayer(prefix + ChatColor.RED + "You are banned. Try again in " + remaining + " seconds.");
                return true;
            } else {
                tempBans.remove(uuid); // Ban expired
            }
        }
        return false;
    }

}
