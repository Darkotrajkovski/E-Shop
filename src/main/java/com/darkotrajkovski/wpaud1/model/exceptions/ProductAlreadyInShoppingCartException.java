package com.darkotrajkovski.wpaud1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException {
    public ProductAlreadyInShoppingCartException(String username, Long id) {
        super(String.format("Product with id: %d already exists in shopping cart for user with username %s", id, username));
    }
}
