package com.darkotrajkovski.wpaud1.service.impl;

import com.darkotrajkovski.wpaud1.model.Role;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.wpaud1.model.exceptions.PasswordsDoNotMatchException;
import com.darkotrajkovski.wpaud1.model.exceptions.UsernameAlreadyExistsException;
import com.darkotrajkovski.wpaud1.repository.jpa.UserRepository;
import com.darkotrajkovski.wpaud1.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) throws InvalidArgumentsException {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
            throw new InvalidArgumentsException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(userRepository.findByUsername(username).isPresent() || !userRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
    }
}
