package org.login.command;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.login.Main;
import org.login.database.dao.UserDatabase;
import org.login.event.PlayerLoginEvent;

public class RegisterCommand implements CommandExecutor {

    public RegisterCommand(Main main) {
        main.getCommand("register").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("register")) {

            Player player = (Player) commandSender;

            if (!Main.getInstance().getLoginManager().contains(player)) {
                player.sendMessage("§cVocê já criou uuma sessão no servidor.");
                return true;
            }

            if (args.length != 2) {
                player.sendMessage("");
                player.sendMessage(" §eDigite §7/registrar <senha> <senha> §epara criar uma sessão no servidor.");
                player.sendMessage("");
                return true;
            }

            if (!args[0].equals(args[1])) {
                player.sendMessage("§cAs duas senhas não são iguais.");
                return true;
            }

            PlayerLoginEvent event = new PlayerLoginEvent(player);

            Bukkit.getPluginManager().callEvent(event);

            if(event.isCancelled()){
                return false;
            }

            UserDatabase userDatabase = new UserDatabase();
            userDatabase.add(player.getName(), args[0]);
            Main.getInstance().getLoginManager().remove(player);
            player.sendMessage("§aSessão registrada com sucesso.");
            player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1f, 1f);
        }
        return false;
    }
}
