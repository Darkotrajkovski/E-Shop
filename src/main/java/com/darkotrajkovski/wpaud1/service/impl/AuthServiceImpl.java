package com.darkotrajkovski.wpaud1.service.impl;

import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidUserCredentialsException;
import com.darkotrajkovski.wpaud1.repository.jpa.UserRepository;
import com.darkotrajkovski.wpaud1.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }
}
