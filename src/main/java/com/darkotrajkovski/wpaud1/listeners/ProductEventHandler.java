package com.darkotrajkovski.wpaud1.listeners;

import com.darkotrajkovski.wpaud1.model.events.ProductCreatedEvent;
import com.darkotrajkovski.wpaud1.service.ProductService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductService productService;

    public ProductEventHandler(ProductService productService) {
        this.productService = productService;
    }

    @EventListener
    public void onProductCreated(ProductCreatedEvent event){
        this.productService.refreshMaterializedView();
    }
}
