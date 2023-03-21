package org.login;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.login.database.SQLProvider;
import org.login.model.login.manager.LoginManager;
import org.login.model.user.manager.UserManager;
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
    private UserManager userManager;

    @Getter
    private BukkitTask userAuthenticatingRunnable;

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
        userManager = new UserManager();
        // Load runnable
        userAuthenticatingRunnable = new UserAuthenticatingRunnable().runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}