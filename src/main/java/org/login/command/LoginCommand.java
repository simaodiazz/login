package org.login.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.login.Main;
import org.login.database.dao.UserDatabase;
import org.login.event.PlayerLoginEvent;

public class LoginCommand implements CommandExecutor {

    public LoginCommand(Main main) {
        main.getCommand("login").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("login")) {

            Player player = (Player) commandSender;

            if (args.length != 1) {
                player.sendMessage("");
                player.sendMessage(" §eDigite §7/login <senha> §epara iniciar sessão no servidor.");
                player.sendMessage("");
                return true;
            }

            UserDatabase userDatabase = new UserDatabase();

            if (userDatabase.contains(player.getName())) {
                player.sendMessage("§cVocê ainda não registrou sessão no servidor");
                return true;
            }

            if (!args[0].equals(userDatabase.find(player.getName()))) {
                player.sendMessage("§cPalavra passe incorreta.");
                return true;
            }

            PlayerLoginEvent event = new PlayerLoginEvent(player);

            Bukkit.getPluginManager().callEvent(event);

            if(event.isCancelled()){
                return false;
            }

            Main.getInstance().getLoginManager().remove(player);
            player.sendMessage("§aSessão iniciada.");
            return true;
        }
        return false;
    }
}