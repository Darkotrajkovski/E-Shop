package com.darkotrajkovski.wpaud1.service;

import com.darkotrajkovski.wpaud1.model.Role;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidArgumentsException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role) throws InvalidArgumentsException;
}
