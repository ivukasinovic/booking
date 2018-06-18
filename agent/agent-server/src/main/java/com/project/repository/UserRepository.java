package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan V. on 18-Jun-18
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
