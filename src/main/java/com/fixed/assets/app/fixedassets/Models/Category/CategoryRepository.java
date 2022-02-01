package com.fixed.assets.app.fixedassets.Models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c FROM category WHERE c.deleteFlag = N", nativeQuery = true)
    List<Category> findCategories();

    Category findByCategoryCode(String code);

    List<Category> findByDeleteFlag(Character deleteFlag);
}
