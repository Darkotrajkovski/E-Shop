package com.darkotrajkovski.wpaud1.repository;

import com.darkotrajkovski.wpaud1.model.Product;
import com.darkotrajkovski.wpaud1.model.views.ProductsPerManufacturerView;
import com.darkotrajkovski.wpaud1.repository.views.ProductsPerManufacturerViewRepository;
import com.darkotrajkovski.wpaud1.service.CategoryService;
import com.darkotrajkovski.wpaud1.service.ManufacturerService;
import com.darkotrajkovski.wpaud1.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsPerManufacturerViewRepositoryTest {

    @Autowired
    private ProductsPerManufacturerViewRepository productsPerManufacturerViewRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateNewProduct(){
        List<ProductsPerManufacturerView> list1 = this.productsPerManufacturerViewRepository.findAll();

        Product product = new Product();
        product.setName("Ski Jacket 557");
        product.setManufacturer(this.manufacturerService.findAll().get(0));
        product.setCategory(this.categoryService.listCategories().get(0));

        this.productService.save(product.getName(), product.getPrice(), product.getQuantity(), product.getCategory().getId(), product.getManufacturer().getId());

        List<ProductsPerManufacturerView> list2 = this.productsPerManufacturerViewRepository.findAll();
    }
}
