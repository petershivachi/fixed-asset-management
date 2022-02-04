package com.fixed.assets.app.fixedassets.Resources;

import com.fixed.assets.app.fixedassets.Models.Asset.Asset;
import com.fixed.assets.app.fixedassets.Models.Asset.AssetRepository;
import com.fixed.assets.app.fixedassets.Models.Asset.AssetService;
import com.fixed.assets.app.fixedassets.Models.Category.Category;
import com.fixed.assets.app.fixedassets.Models.Category.CategoryRepository;
import com.fixed.assets.app.fixedassets.Models.Category.CategoryService;
import com.fixed.assets.app.fixedassets.Responses.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/categories")
@Slf4j
public class CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private AssetService assetService;

    // Method      GET
    // Description Endpoint to get all categories
    // Access Private
    @GetMapping("all")
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    // Method      GET
    // Description Endpoint to get a category by category code
    // Access Private
    @GetMapping("{categoryCode}")
    @ResponseBody
    public  ResponseEntity<?> getCategoryByCode(@PathVariable String categoryCode){
        Category categoryExists = categoryService.getCategory(categoryCode);

        if (categoryExists == null){
            return  ResponseEntity.badRequest().body(new MessageResponse("Category with the code " + categoryCode + " not found"));
        }

        if(categoryExists.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code " + categoryCode + " not found"));
        }

        return ResponseEntity.ok().body(categoryExists);
    }

    // Method      POST
    // Description Endpoint to update a category by the category id
    // Access Private
    @PostMapping("add")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        // Assert that similar category does not exist;
        Category categoryExists = categoryService.getCategory(category.getCategoryCode());

        if(categoryExists != null){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code " + category.getCategoryCode() + " already exists"));
        }

        Category newCategory = categoryService.addCategory(category);

        return ResponseEntity.ok().body(category);
    }

    // Method      PUT
    // Description Endpoint to update a category
    // Access Private
    @PutMapping("{categoryCode}")
    public ResponseEntity<?> updateCategory(@RequestBody Category category, @PathVariable String categoryCode){
        Category categoryToUpdate = categoryService.getCategory(categoryCode);

        if (categoryToUpdate == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code "+ categoryCode + " not found"));
        }
        if(categoryToUpdate.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code "+ categoryCode + " not found"));
        }

        categoryToUpdate.setCategoryName(category.getCategoryName());
        categoryToUpdate.setCategoryDescription(category.getCategoryDescription());

        categoryService.updateCategory(categoryToUpdate);

        return ResponseEntity.ok().body(categoryToUpdate);
    }

    // Method      PUT
    // Description Endpoint to remove a category
    // Access Private
    @PutMapping("{categoryCode}/delete")
    public ResponseEntity<?> deleteCategory(@PathVariable String categoryCode){
        Category category = categoryService.getCategory(categoryCode);

        if(category == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code " + categoryCode + " not found"));
        }

        List<Asset> assets = new ArrayList<Asset>();

        assets = category.getAssets();

        if (assets.size() != 0){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the code " + categoryCode + " cannot be deleted because it's not empty"));
        }

        category.setDeleteFlag('Y');

        categoryService.deleteCategory(category);

        return ResponseEntity.ok().body(new MessageResponse("Category with the code " + categoryCode + " removed successfully"));
    }
}
