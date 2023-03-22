package org.login;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.login.command.LoginCommand;
import org.login.command.RegisterCommand;
import org.login.database.SQLProvider;
import org.login.listeners.PlayerJoinListener;
import org.login.listeners.PlayerMoveListener;
import org.login.login.manager.LoginManager;
import org.login.runnable.UserAuthenticatingRunnable;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    @Getter
    @Setter
    private HikariDataSource hikari;

    @Getter
    private LoginManager loginManager;

    @Getter
    private UserAuthenticatingRunnable userAuthenticatingRunnable;

    @Override
    public void onLoad() {
        instance = this;
        // Saving config.yml
        saveDefaultConfig();
        // Setup mysql
        SQLProvider sqlProvider = new SQLProvider();
        sqlProvider.setup(this);
        // Load all managers
        loginManager = new LoginManager();
        // Make all players in server login again
        Bukkit.getOnlinePlayers().forEach(player -> {
            loginManager.add(player);
        });
    }

    @Override
    public void onEnable() {
        // Load runnable
        userAuthenticatingRunnable = new UserAuthenticatingRunnable();
        // Register listeners
        new PlayerJoinListener(this);
        new PlayerMoveListener(this);
        // Register commands
        new RegisterCommand(this);
        new LoginCommand(this);
    }

    @Override
    public void onDisable() {

    }
}