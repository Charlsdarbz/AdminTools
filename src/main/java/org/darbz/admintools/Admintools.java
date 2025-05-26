package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Admintools extends JavaPlugin {

    private static Admintools instance;
    public static String prefix;

    private TempBan tempBan;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        prefix = ChatColor.translateAlternateColorCodes('&',
                getConfig().getString("prefix", "&6[&bAdminTools&6]&r"));

        getLogger().info(prefix + ChatColor.YELLOW + " Has Loaded");

        tempBan = new TempBan(this);

        getCommand("Freeze").setExecutor(new Freeze());
        getCommand("Vanish").setExecutor(new Vanish(this));
        getCommand("Broadcast").setExecutor(new Broadcast());
        getCommand("ItemCreate").setExecutor(new ItemCreate());
        getCommand("Heal").setExecutor(new Heal());
        getCommand("Pvp").setExecutor(new pvptoggle());
        getCommand("effect_checker").setExecutor(new PotionEffectChecker());
        getCommand("tempban").setExecutor(tempBan);
        getCommand("fly").setExecutor(new fly());

        getCommand("ItemCreate").setTabCompleter(new ItemCreateTabCompleter());

        getServer().getPluginManager().registerEvents(new FreezeListener(), this);
        getServer().getPluginManager().registerEvents(new pvplistener(), this);
        getServer().getPluginManager().registerEvents(new TbanListener(tempBan), this);
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

    public TempBan getTempBan() {
        return tempBan;
    }
}
