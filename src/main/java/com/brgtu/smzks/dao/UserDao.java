package com.brgtu.smzks.dao;

import com.brgtu.smzks.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by yauheni on 12.03.16.
 */

public interface UserDao extends CrudRepository<User, Long> {
}
