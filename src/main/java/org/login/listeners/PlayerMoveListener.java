package org.login.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.login.Main;

public class PlayerMoveListener implements Listener {

    public PlayerMoveListener(Main main) {
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();
        if (Main.getInstance().getLoginManager().contains(player)) {
            if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
                event.setTo(from);
            }
        }
    }
}