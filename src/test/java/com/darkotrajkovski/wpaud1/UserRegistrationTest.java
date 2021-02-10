package com.darkotrajkovski.wpaud1;

import com.darkotrajkovski.wpaud1.model.Role;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.InvalidArgumentsException;
import com.darkotrajkovski.wpaud1.repository.jpa.UserRepository;
import com.darkotrajkovski.wpaud1.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");
    }

    @Test
    public void testSuccessRegister(){
        User user = this.service.register("username", "password", "password", "name", "surname", Role.ROLE_USER);
        Mockito.verify(this.service).register("username", "password", "password", "name", "surname", Role.ROLE_USER);

        Assert.assertNotNull("User is null", user);

        Assert.assertEquals("Name do not match", "name", user.getName());
        Assert.assertEquals("Role do not match", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("Surname do not match", "surname", user.getSurname());
        Assert.assertEquals("Password do not match", "password", user.getPassword());
        Assert.assertEquals("Username do not match", "username", user.getUsername());
    }

    @Test
    public void testNullUsername(){
        Assert.assertThrows("InvalidArgumentException expected", InvalidArgumentsException.class, () -> this.service.register(null,"password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(null,"password", "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername(){
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected", InvalidArgumentsException.class, () -> this.service.register(username,"password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.service).register(username,"password", "password", "name", "surname", Role.ROLE_USER);
    }
}
