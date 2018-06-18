package com.project.services;

import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ivan V. on 18-Jun-18
 */
@Service
public class RepositoryService {

    @Autowired
    private UserRepository userRepository;

    public  User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
