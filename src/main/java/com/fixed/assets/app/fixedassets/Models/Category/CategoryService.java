package com.fixed.assets.app.fixedassets.Models.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
       return categoryRepository.findByDeleteFlag('N');
    }

    public Category addCategory(Category category){
        category.setDeleteFlag('N');
        category.setRcre(new Date());
        return categoryRepository.save(category);
    }

    public Category getCategory(String categoryCode){
        return categoryRepository.findByCategoryCode(categoryCode);
    }

    public void updateCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategory(Category category){
        categoryRepository.save(category);
    }

}
