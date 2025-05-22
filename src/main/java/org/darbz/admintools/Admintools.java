package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Admintools extends JavaPlugin {

    private static Admintools instance;
    private static String prefix;

    @Override
    public void onEnable() {
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        prefix = ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("prefix", "&6[&bAdminTools&6]&r"));

        getServer().getLogger().info(prefix + ChatColor.YELLOW + " Has Loaded");

        getCommand("Freeze").setExecutor(new Freeze());
        getCommand("Vanish").setExecutor(new Vanish(this));
        getCommand("Broadcast").setExecutor(new Broadcast());

        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static Admintools getInstance() {
        return instance;
    }

    public static String getPrefix() {
        return prefix;
    }
}
