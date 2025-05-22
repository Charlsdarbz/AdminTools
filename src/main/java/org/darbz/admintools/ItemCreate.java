package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

import static org.bukkit.enchantments.Enchantment.MENDING;

public class ItemCreate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            if (args.length == 5) {
                ItemStack item = new ItemStack(Material.valueOf(args[4].toUpperCase()), 1);

                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(args[0]);
                    meta.setLore(Collections.singletonList(args[2]));
                    meta.setCustomModelData(Integer.valueOf(args[1]));
                    int maxStackSize = Integer.valueOf(args[3]);
                    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
                    meta.addEnchant(Enchantment.MENDING, 1, true);
                    meta.setMaxStackSize(maxStackSize);

                    item.setItemMeta(meta);


                    Player player = (Player) commandSender;
                    player.getInventory().addItem(item);

                    player.sendMessage(ChatColor.DARK_GRAY + "#======Item Info======#");

                    player.sendMessage(ChatColor.AQUA + "Custom Model Data:" + " " + args[1]);
                    player.sendMessage(ChatColor.RED + "ItemLore:" + " " + args[2]);
                    player.sendMessage(ChatColor.GOLD + "Name:" + " " + args[0]);
                    player.sendMessage(ChatColor.BLUE + "Max Stack Size:" + " " + args[3]);
                    player.sendMessage(ChatColor.DARK_RED + "Item Type:" + " " + args[4]);
                    player.sendMessage(ChatColor.DARK_GRAY + "#===================#");

                }
            }
        }
        return false;
    }
}