package com.ssaju.expensemanager.repository;

import com.ssaju.expensemanager.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ssaju on 2/9/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
