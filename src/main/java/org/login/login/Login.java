package org.login.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;

@AllArgsConstructor
@Data
public class Login {

    private Player user;
    private long timeKick;

}