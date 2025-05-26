package org.darbz.admintools;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class TbanListener implements Listener {

    private final TempBan tempBan;
    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.RED + "§lBan" + ChatColor.DARK_GRAY + "] ";

    public TbanListener(TempBan tempBan) {
        this.tempBan = tempBan;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (tempBan.isBanned(player)) {
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            tempBan.isBanned(player);
        }
    }
}
