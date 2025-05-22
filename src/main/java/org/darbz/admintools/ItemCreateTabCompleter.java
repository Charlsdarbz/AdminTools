package org.darbz.admintools;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class ItemCreateTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return switch (args.length) {
            case 1 -> Collections.singletonList("<item_name>");
            case 2 -> Collections.singletonList("<custom_model_data>");
            case 3 -> Collections.singletonList("<Item_lore>");
            case 4 -> Collections.singletonList("<Max_Stack_Size>");
            case 5 -> Collections.singletonList("<Item_Type>");
            default -> Collections.emptyList();
        };
    }
}

