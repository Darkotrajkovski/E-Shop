package com.darkotrajkovski.wpaud1.service.impl;

import com.darkotrajkovski.wpaud1.model.Product;
import com.darkotrajkovski.wpaud1.model.ShoppingCart;
import com.darkotrajkovski.wpaud1.model.User;
import com.darkotrajkovski.wpaud1.model.enumerations.ShoppingCartStatus;
import com.darkotrajkovski.wpaud1.model.exceptions.ProductAlreadyInShoppingCartException;
import com.darkotrajkovski.wpaud1.model.exceptions.ProductNotFoundException;
import com.darkotrajkovski.wpaud1.model.exceptions.ShoppingCartNotFoundException;
import com.darkotrajkovski.wpaud1.model.exceptions.UserNotFoundException;
import com.darkotrajkovski.wpaud1.repository.jpa.ShoppingCartRepository;
import com.darkotrajkovski.wpaud1.repository.jpa.UserRepository;
import com.darkotrajkovski.wpaud1.service.ProductService;
import com.darkotrajkovski.wpaud1.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = getActiveShoppingCart(username);
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts().stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0) throw new ProductAlreadyInShoppingCartException(username, productId);
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
