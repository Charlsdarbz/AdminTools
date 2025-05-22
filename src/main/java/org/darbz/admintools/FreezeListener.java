package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import static org.darbz.admintools.Freeze.Frozen;

public class FreezeListener implements Listener {

    @EventHandler
    public void Playermove(PlayerMoveEvent e) {
        if (Frozen.contains(e.getPlayer().getUniqueId())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You Are Currently Frozen!");
        }
    }

    @EventHandler
    public void PlayerShoot(ProjectileLaunchEvent e) {
        if (!(e.getEntity() instanceof Projectile)) return;

        Projectile projectile = (Projectile) e.getEntity();
        if (projectile.getShooter() instanceof Player) {
            Player player = (Player) projectile.getShooter();
            if (e.getEntityType() == EntityType.ENDER_PEARL) {
                if (Frozen.contains(player.getUniqueId())) {
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You Cant Ender Pearl Whilst Frozen!");
                }
            }
        }
    }

    @EventHandler
    public void PlayerTP(PlayerTeleportEvent e) {
        if (Frozen.contains(e.getPlayer().getUniqueId())) {
            if (e.getCause() == PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT) {
                e.getPlayer().sendMessage(ChatColor.RED + "You Cant Use This Whilst Frozen!");
            }
        }
    }
}


