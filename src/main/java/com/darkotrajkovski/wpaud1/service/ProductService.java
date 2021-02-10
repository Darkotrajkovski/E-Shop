package com.darkotrajkovski.wpaud1.service;

import com.darkotrajkovski.wpaud1.model.Product;
import com.darkotrajkovski.wpaud1.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    void deleteById(Long id);
    Optional<Product> save(ProductDto productDto);
    Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long category, Long manufacturer);
    Optional<Product> edit(Long id, ProductDto productDto);
}
