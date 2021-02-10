package com.darkotrajkovski.wpaud1.service;

import com.darkotrajkovski.wpaud1.model.Product;
import com.darkotrajkovski.wpaud1.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
