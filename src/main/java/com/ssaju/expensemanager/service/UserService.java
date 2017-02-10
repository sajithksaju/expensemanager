package com.ssaju.expensemanager.service;

import com.ssaju.expensemanager.model.User;

/**
 * Created by ssaju on 2/9/17.
 */
public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
