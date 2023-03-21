package org.login.model.user.manager;

import org.login.model.user.User;

import java.util.HashMap;

public class UserManager {

    private final HashMap<String, User> cache;

    public UserManager() {
        this.cache = new HashMap<>();
    }

    public boolean contains(String playerName) {
        return cache.containsKey(playerName);
    }

    public User get(String playerName) {
        return cache.get(playerName);
    }

    public void add(User user) {
        cache.put(user.getPlayerName(), user);
    }

    public void remove(String playerName) {
        cache.remove(playerName);
    }
}