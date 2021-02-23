package com.darkotrajkovski.wpaud1.repository.jpa;

import com.darkotrajkovski.wpaud1.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
                attributePaths = {"carts"})
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts", "discount"})
    @Query("select u from User u")
    List<User> loadAll();

    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
