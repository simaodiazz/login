package org.login.model.login.manager;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.login.Main;
import org.login.database.dao.UserDatabase;
import org.login.model.login.Login;
import org.login.model.login.action.LoginType;

import java.util.HashMap;
import java.util.Set;

public class LoginManager {

    private final HashMap<String, Login> cache;

    public LoginManager() {
        cache = new HashMap<>();
    }

    public boolean contains(Player player) {
        return cache.containsKey(player.getName());
    }

    public Login get(String playerName) {
        return cache.get(playerName);
    }

    public final Set<String> getAll() {
        return cache.keySet();
    }

    public void add(Player player) {
        UserDatabase userDatabase = new UserDatabase();
        userDatabase.contains(player.getName()).thenAccept(userExists -> {
            if (userExists) {
                player.sendMessage("");
                player.sendMessage(" §eDigite /login <senha> para iniciar sessão no servidor.");
                player.sendMessage("");
                Login signin = new Login(Main.getInstance().getUserManager().get(player.getName()), LoginType.SIGN_IN, System.currentTimeMillis() + 60000L);
                cache.put(player.getName(), signin);
            } else {
                player.sendMessage("");
                player.sendMessage(" §eDigite /registrar <senha> <senha> para criar uma sessão no servidor.");
                player.sendMessage("");
                Login signup = new Login(Main.getInstance().getUserManager().get(player.getName()), LoginType.SIGN_UP, System.currentTimeMillis() + 60000L);
                cache.put(player.getName(), signup);
            }
        });
    }

    public void remove(String playerName) {
        cache.remove(playerName);
    }
}