package com.darkotrajkovski.wpaud1.repository.jpa;

import com.darkotrajkovski.wpaud1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);
}
