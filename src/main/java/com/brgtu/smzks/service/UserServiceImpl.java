package com.brgtu.smzks.service;

import com.brgtu.smzks.dao.RoleDao;
import com.brgtu.smzks.dao.UserDao;
import com.brgtu.smzks.dto.FactoryTo;
import com.brgtu.smzks.dto.UserTo;
import com.brgtu.smzks.model.Role;
import com.brgtu.smzks.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yauheni on 13.03.16.
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    FactoryTo factoryTo;

    @Override
    public User saveUser(UserTo userTo) {
        User user = factoryTo.getUser(userTo);
        List<Role> roles = new ArrayList<>();

        user = userDao.save(user);

        Role role = new Role();
        role.setRole("ROLE_ADMIN");
        role.setUser(user);
        role = roleDao.save(role);
        roles.add(role);
        user.setRoles(roles);

        user = userDao.save(user);

        return user;
    }
}
