package com.darkotrajkovski.wpaud1.repository.jpa;

import com.darkotrajkovski.wpaud1.model.ShoppingCart;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
