package com.darkotrajkovski.wpaud1.model.events;

import com.darkotrajkovski.wpaud1.model.Product;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ProductCreatedEvent extends ApplicationEvent {

    private LocalDateTime when;

    public ProductCreatedEvent(Product source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public ProductCreatedEvent(Product source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
