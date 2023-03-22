package org.login.database.dao;

import java.util.HashMap;

public interface UserDatabaseService {

    boolean contains(String playerName);

    String find(String playerName);

    HashMap<String, String> findAll();

    void add(String playerName, String password);

    void update(String playerName, String password);

    void delete(String playerName);

}