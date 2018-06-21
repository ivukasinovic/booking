package com.project.services;

import com.project.model.User;
import com.project.repository.AdditionServiceRepository;
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

    @Autowired
    private AdditionServiceRepository additionServiceRepository;

    public  User findByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
