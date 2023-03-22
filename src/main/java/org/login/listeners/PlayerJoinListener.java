package org.login.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.login.Main;

public class PlayerJoinListener implements Listener {

    public PlayerJoinListener(Main main) {
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.getInstance().getLoginManager().add(player);
    }
}