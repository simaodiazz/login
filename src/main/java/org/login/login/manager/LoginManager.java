package org.login.login.manager;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.login.database.dao.UserDatabase;
import org.login.login.Login;
import org.login.login.action.LoginType;

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

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 1);
        player.addPotionEffect(potionEffect);
        player.teleport(player.getWorld().getSpawnLocation());

        if (userDatabase.contains(player.getName())) {
            player.sendMessage("");
            player.sendMessage(" §eDigite §7/login <senha> §epara iniciar sessão no servidor.");
            player.sendMessage("");
            Login signin = new Login(player, LoginType.SIGN_IN, System.currentTimeMillis() + 60000L);
            cache.put(player.getName(), signin);
        } else {
            player.sendMessage("");
            player.sendMessage(" §eDigite §7/registrar <senha> <senha> §epara criar uma sessão no servidor.");
            player.sendMessage("");
            Login signup = new Login(player, LoginType.SIGN_UP, System.currentTimeMillis() + 60000L);
            cache.put(player.getName(), signup);
        }
    }

    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.BLINDNESS);
        cache.remove(player.getName());
    }
}