package com.darkotrajkovski.wpaud1.repository.views;

import com.darkotrajkovski.wpaud1.model.views.ProductsPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsPerCategoryViewRepository extends JpaRepository<ProductsPerCategoryView, Long> {

}
