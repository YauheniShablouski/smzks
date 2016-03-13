package com.brgtu.smzks.service;

import com.brgtu.smzks.dto.UserTo;
import com.brgtu.smzks.model.User;

/**
 * Created by yauheni on 13.03.16.
 */
public interface UserService {
    public User saveUser(UserTo userTo);

}
