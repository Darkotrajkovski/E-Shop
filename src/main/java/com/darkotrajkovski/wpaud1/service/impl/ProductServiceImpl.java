package com.darkotrajkovski.wpaud1.service.impl;

import com.darkotrajkovski.wpaud1.model.Category;
import com.darkotrajkovski.wpaud1.model.Manufacturer;
import com.darkotrajkovski.wpaud1.model.Product;
import com.darkotrajkovski.wpaud1.model.dto.ProductDto;
import com.darkotrajkovski.wpaud1.model.events.ProductCreatedEvent;
import com.darkotrajkovski.wpaud1.model.exceptions.CategoryNotFoundException;
import com.darkotrajkovski.wpaud1.model.exceptions.ManufacturerNotFoundException;
import com.darkotrajkovski.wpaud1.model.exceptions.ProductNotFoundException;
import com.darkotrajkovski.wpaud1.repository.jpa.CategoryRepository;
import com.darkotrajkovski.wpaud1.repository.jpa.ManufacturerRepository;
import com.darkotrajkovski.wpaud1.repository.jpa.ProductRepository;
import com.darkotrajkovski.wpaud1.repository.views.ProductsPerManufacturerViewRepository;
import com.darkotrajkovski.wpaud1.service.ProductService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository, ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productsPerManufacturerViewRepository = productsPerManufacturerViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        productRepository.deleteByName(name);
        Product product = new Product(name, price, quantity, category, manufacturer);
        productRepository.save(product);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> save(ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));

        this.productRepository.deleteByName(productDto.getName());
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), category, manufacturer);
        productRepository.save(product);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new ProductCreatedEvent(product));
        return Optional.of(product);
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        productRepository.save(product);
        //this.refreshMaterializedView();
        return Optional.of(product);
    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(productDto.getManufacturer())
                .orElseThrow(() -> new ManufacturerNotFoundException(productDto.getManufacturer()));
        product.setManufacturer(manufacturer);

        productRepository.save(product);
        //this.refreshMaterializedView();
        return Optional.of(product);
    }

    @Override
    public void refreshMaterializedView() {
        this.productsPerManufacturerViewRepository.refreshMaterializedView();
    }
}
