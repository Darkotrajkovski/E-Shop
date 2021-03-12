package com.darkotrajkovski.wpaud1.repository;

import com.darkotrajkovski.wpaud1.model.Role;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.exceptions.UserNotFoundException;
import com.darkotrajkovski.wpaud1.model.projections.UserProjection;
import com.darkotrajkovski.wpaud1.repository.jpa.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAll(){
        List<User> userList = this.userRepository.findAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testFetchAll(){
        List<User> userList = this.userRepository.fetchAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testLoadAll(){
        List<User> userList = this.userRepository.loadAll();
        Assert.assertEquals(2L, userList.size());
    }

    @Test
    public void testProjectUsernameAndNameAndSurname(){
        UserProjection userProjection = this.userRepository.findByRole(Role.ROLE_USER);
        Assert.assertEquals("darko.trajkovski", userProjection.getUsername());
        Assert.assertEquals("Darko", userProjection.getName());
        Assert.assertEquals("Trajkovski", userProjection.getSurname());
    }

    @Test
    public void testOptimisticLock(){
        User user1 = this.userRepository.findByUsername("user")
                .orElseThrow(() -> new UserNotFoundException("user"));
        User user2 = this.userRepository.findByUsername("user")
                .orElseThrow(() -> new UserNotFoundException("user"));

        user1.setName("user1");
        user2.setName("user2");

        this.userRepository.save(user1);
        this.userRepository.save(user2);
    }
}
