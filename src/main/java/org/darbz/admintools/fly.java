package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        boolean currentlyFlying = player.getAllowFlight();
        player.setAllowFlight(!currentlyFlying);
        player.setFlying(!currentlyFlying);

        player.sendMessage(ChatColor.AQUA + "Flight " + (currentlyFlying ? ChatColor.RED + "disabled" : ChatColor.GREEN + "enabled") + ChatColor.AQUA + ".");
        return true;
    }
}
