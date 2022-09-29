package com.brunopassos.desafioimpa.model.service;

import com.brunopassos.desafioimpa.model.domain.User;
import com.brunopassos.desafioimpa.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User insert(User user){
        return userRepository.save(user);
    }
}
