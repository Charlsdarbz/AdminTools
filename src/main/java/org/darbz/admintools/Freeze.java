package org.darbz.admintools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Freeze implements CommandExecutor {
    public static List<UUID> Frozen = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            String prefix = Admintools.getPrefix();

            if (args.length == 0) {
                player.sendMessage(prefix + ChatColor.RED + " You Can Only Freeze Other Players!");
            } else if (args.length == 1 && args[0].equalsIgnoreCase("List")) {
                if (Frozen.isEmpty()) {
                    player.sendMessage(prefix + ChatColor.RED + " No players are currently frozen!");
                } else {
                    player.sendMessage(prefix + ChatColor.AQUA + " These players are currently frozen:");
                    for (UUID id : Frozen) {
                        OfflinePlayer frozenPlayer = Bukkit.getOfflinePlayer(id);
                        player.sendMessage(prefix + ChatColor.GRAY + "- " + frozenPlayer.getName());
                    }
                }
            } else {
                String playername = args[0];
                Player target = Bukkit.getServer().getPlayerExact(playername);

                if (target == null) {
                    player.sendMessage(prefix + ChatColor.RED + " This player is not online");
                    return false;
                }

                if (!Frozen.contains(target.getUniqueId())) {
                    Frozen.add(target.getUniqueId());
                    target.sendMessage(prefix + ChatColor.RED + " You Have Been" + ChatColor.AQUA + " Frozen By " + ChatColor.RED + player.getName());
                    player.sendMessage(prefix + ChatColor.GRAY + " You have frozen " + playername);
                } else {
                    Frozen.remove(target.getUniqueId());
                    target.sendMessage(prefix + ChatColor.AQUA + " You Are No Longer Frozen!");
                    player.sendMessage(prefix + ChatColor.GRAY + " You have unfrozen " + playername);
                }
            }
        }
        return false;
    }
}
