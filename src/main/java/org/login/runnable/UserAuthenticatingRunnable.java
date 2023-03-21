package org.login.runnable;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.login.Main;
import org.login.utils.ActionBar;

public class UserAuthenticatingRunnable extends BukkitRunnable  {

    public UserAuthenticatingRunnable() {
        this.runTaskTimer(Main.getInstance(), 0L, 20L);
    }

    @Override
    public void run() {

        for (String playerName : Main.getInstance().getLoginManager().getAll()) {

            Player player = Bukkit.getPlayer(playerName);

            if (Main.getInstance().getLoginManager().get(playerName).getKickDelay() > System.currentTimeMillis()) {

                long time = (Main.getInstance().getLoginManager().get(playerName).getKickDelay() - System.currentTimeMillis()) / 1000;

                ActionBar.send(player, String.format("Você possui %s para iniciar sessão ou registrar.", time));

                continue;
            }

            player.kickPlayer("§cExcesso de tempo para iniciar sessão ou criar conta.");
        }
    }
}