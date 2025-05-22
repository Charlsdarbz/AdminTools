package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import static org.darbz.admintools.pvptoggle.Enabled;

public class pvplistener implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent entityEvent) {
            Entity damager = entityEvent.getDamager();
            if (damager instanceof Player) {
                if (!Enabled) {
                    event.setCancelled(true);
                    damager.sendMessage(ChatColor.RED + "§lPvP is currently disabled!");
                }
            }
        }
    }
}
