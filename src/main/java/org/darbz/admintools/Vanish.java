package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Vanish implements CommandExecutor {
    private final Set<UUID> vanished = new HashSet<>();
    private final Plugin plugin;

    public Vanish(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = Admintools.getPrefix();

        if(sender instanceof Player player) {

            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("list")) {
                    player.sendMessage(prefix + ChatColor.DARK_GRAY + " These players are currently vanished:");
                    for (UUID id : vanished) {
                        OfflinePlayer vanishedPlayer = Bukkit.getOfflinePlayer(id);
                        player.sendMessage(prefix + ChatColor.GRAY + "- " + vanishedPlayer.getName());
                    }
                    return true;
                }
            }

            if (vanished.contains(player.getUniqueId())) {
                vanished.remove(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.showPlayer(plugin, player);
                }
                player.sendMessage(prefix + ChatColor.GRAY + " You are no longer vanished.");
            } else {
                vanished.add(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()) {
                    target.hidePlayer(plugin, player);
                }
                player.sendMessage(prefix + ChatColor.GRAY + " You are now vanished.");
            }
            return true;
        } else {
            sender.sendMessage(prefix + ChatColor.RED + " Only Players Can Use This Command!");
            return false;
        }
    }
}
