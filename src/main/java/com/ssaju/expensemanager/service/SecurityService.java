package com.ssaju.expensemanager.service;

/**
 * Created by ssaju on 2/9/17.
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
