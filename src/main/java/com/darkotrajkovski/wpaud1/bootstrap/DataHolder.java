package com.darkotrajkovski.wpaud1.bootstrap;

import com.darkotrajkovski.wpaud1.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    /*@PostConstruct
    public void init(){
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Movies", "Movies category"));
        Category category = new Category("Sport", "Sport Category");
        categories.add(category);

        users.add(new User("darko.trajkovski", "dt", "Darko", "Trajkovski"));
        users.add(new User("petar.petrov", "pp", "Petar", "Petrov"));

        Manufacturer manufacturer = new Manufacturer("Nike", "NY NY");
        manufacturers.add(manufacturer);
        manufacturers.add(new Manufacturer("Apple", "LA LA"));

        products.add(new Product("Ball1", 235.8, 7, category, manufacturer));
        products.add(new Product("Ball2", 235.8, 7, category, manufacturer));
        products.add(new Product("Ball3", 235.8, 7, category, manufacturer));
    }*/
}
