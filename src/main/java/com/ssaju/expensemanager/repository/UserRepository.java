package com.ssaju.expensemanager.repository;

import com.ssaju.expensemanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ssaju on 2/9/17.
 */
public interface  UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
