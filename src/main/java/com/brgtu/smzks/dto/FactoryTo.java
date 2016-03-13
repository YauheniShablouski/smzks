package com.brgtu.smzks.dto;

import com.brgtu.smzks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by yauheni on 13.03.16.
 */

@Component
public class FactoryTo {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(UserTo userTo) {
        User user = new User();

        user.setEnabled(true);
        user.setName(userTo.getName());
        user.setUsername(userTo.getUsername());
        user.setPassword( passwordEncoder.encode(userTo.getPassword()) );

        return user;
    }
}
