package com.darkotrajkovski.wpaud1.service;

import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidUserCredentialsException;

public interface AuthService {
    User login(String username, String password) throws InvalidUserCredentialsException, InvalidArgumentsException;
}
