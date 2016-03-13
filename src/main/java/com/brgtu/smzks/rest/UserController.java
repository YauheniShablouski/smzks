package com.brgtu.smzks.rest;

import com.brgtu.smzks.dao.RoleDao;
import com.brgtu.smzks.dao.UserDao;
import com.brgtu.smzks.dto.FactoryTo;
import com.brgtu.smzks.dto.UserTo;
import com.brgtu.smzks.model.Role;
import com.brgtu.smzks.model.User;
import com.brgtu.smzks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yauheni on 12.03.16.
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @RequestMapping(value = "/signup", method= RequestMethod.POST)
    public UserTo signUp(@RequestBody UserTo userTo ) {
        userService.saveUser(userTo);
        return  userTo;
    }

}
