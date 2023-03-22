package org.login.database.dao;

import org.bukkit.Bukkit;
import org.login.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDatabase implements UserDatabaseService {

    @Override
    public boolean contains(String playerName) {
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT playerName FROM users WHERE playerName=?")) {
            preparedStatement.setString(1, playerName);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Erro ao tentar verificar se o `playerName` existia em `users`");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String find(String playerName) {
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE playerName=?")) {
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Não foi possível adquirir `playerName` em `users`");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<String, String> findAll() {
        HashMap<String, String> users = new HashMap<>();
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.put(resultSet.getString("playerName"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Não foi possível adquirir todas os user da tabela `users`");
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void add(String playerName, String password) {
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (playerName, password) VALUES (?,?)")) {
            statement.setString(1, playerName);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Erro ao tentar criar a conta de um usuário em `users`");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String playerName, String password) {
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password=? WHERE playerName=?")) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, playerName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Erro ao tentar atualizar a senha de um usuário em `users`");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String playerName) {
        try (Connection connection = Main.getInstance().getHikari().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE playerName=? AND password=?")) {
            preparedStatement.setString(1, playerName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§c[Lobby] Erro ao tentar deletar a conta de `playerName`");
        }
    }
}