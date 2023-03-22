package org.login.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.entity.Player;
import org.login.login.action.LoginType;

@AllArgsConstructor
@Data
public class Login {

    private Player user;
    private LoginType loginType;
    private long timeKick;

}