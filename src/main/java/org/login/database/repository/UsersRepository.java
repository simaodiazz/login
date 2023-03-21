package org.login.database.repository;

import org.bukkit.Bukkit;
import org.login.Main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsersRepository implements DatabaseRepository {

    @Override
    public void create(Main main) {
        try (PreparedStatement preparedStatement = main.getHikari().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS users (playerName VARCHAR(16) NOT NULL, password VARCHAR(48) NOT NULL)")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Não foi possível criar a tabela `users`");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Main main) {
        try (PreparedStatement preparedStatement = main.getHikari().getConnection().prepareStatement("DELETE FROM users")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Não foi possível deletar a tabela `users`");
            throw new RuntimeException(e);
        }
    }
}