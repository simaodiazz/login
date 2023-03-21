package org.login.database.dao;

import org.login.model.user.User;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface UserDatabaseService {

    CompletableFuture<Boolean> contains(String playerName);
    CompletableFuture<User> find(String playerName);
    CompletableFuture<Set<User>> findAll();
    CompletableFuture<Void> add(String playerName, String password);
    CompletableFuture<Void> update(String playerName, String password);
    CompletableFuture<Void> delete(String playerName);

}