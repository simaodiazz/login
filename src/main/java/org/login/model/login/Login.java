package org.login.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.login.model.login.action.LoginType;
import org.login.model.user.User;

@AllArgsConstructor
@Data
public class Login {

    private User user;
    private LoginType loginType;
    private long timeKick;

}