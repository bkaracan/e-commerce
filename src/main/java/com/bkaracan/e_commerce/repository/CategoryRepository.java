package com.bkaracan.e_commerce.repository;

import com.bkaracan.e_commerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    boolean existsByName(String categoryName);
    boolean existsByNameAndIdNot(String categoryName, Long id);
}
