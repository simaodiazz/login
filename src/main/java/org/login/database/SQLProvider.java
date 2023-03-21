package org.login.database;

import com.zaxxer.hikari.HikariDataSource;
import org.login.Main;
import org.login.database.repository.UsersRepository;

public class SQLProvider {

    public void setup(Main main) {
        main.setHikari(new HikariDataSource());

        main.getHikari().setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        main.getHikari().addDataSourceProperty("serverName", main.getConfig().getString("mysql.serverName"));
        main.getHikari().addDataSourceProperty("port", main.getConfig().getString("mysql.port"));
        main.getHikari().addDataSourceProperty("databaseName", main.getConfig().getString("mysql.databaseName"));
        main.getHikari().addDataSourceProperty("user", main.getConfig().getString("mysql.user"));
        main.getHikari().addDataSourceProperty("password", main.getConfig().getString("mysql.password"));

        UsersRepository usersRepository = new UsersRepository();
        usersRepository.create(main);

    }
}