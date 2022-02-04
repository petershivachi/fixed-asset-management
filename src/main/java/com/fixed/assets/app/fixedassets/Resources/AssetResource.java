package com.fixed.assets.app.fixedassets.Resources;

import com.fixed.assets.app.fixedassets.Models.Asset.Asset;
import com.fixed.assets.app.fixedassets.Models.Asset.AssetService;
import com.fixed.assets.app.fixedassets.Models.Category.Category;
import com.fixed.assets.app.fixedassets.Models.Category.CategoryService;
import com.fixed.assets.app.fixedassets.Responses.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/v1/assets")
public class AssetResource {
    @Autowired
    AssetService assetService;

    @Autowired
    private CategoryService categoryService;

    // Method      GET
    // Description Endpoint to get all assets
    // Access Private
    @GetMapping("all")
        public ResponseEntity<?> getAllAssets() {
        return ResponseEntity.ok().body(assetService.getAllAssets());
    }

    // Method      GET
    // Description  Endpoint to get an asset via the asset code
    // Access   Private
    @GetMapping("{assetCode}")
    public ResponseEntity<?> getAssetById(@PathVariable String assetCode){
        Asset asset = assetService.getAssetById(assetCode);

        if(asset == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code " + assetCode + " not found"));
        }
        if(asset.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code " + assetCode + " not found"));
        }

        return ResponseEntity.ok().body(assetService.getAssetById(assetCode));
    }

    // Method      POST
    // Description  Endpoint to add an asset
    // Access   Private
    @PostMapping("add-asset")
    public ResponseEntity<?> addAssets(@RequestBody Asset asset) {
        // Affirm that asset does not exist in the system
        Asset assetExists = assetService.getAssetById(asset.getAssetCode());

        if(assetExists != null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code " + asset.getAssetCode() + " already exists"));
        }

        Category category = categoryService.getCategory(asset.getCategoryCode());

        if(category == null){
            return  ResponseEntity.badRequest().body(new MessageResponse("Category with the category code " + asset.getCategoryCode() + " not found"));
        }

        if(category.getDeleteFlag() =='Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Category with the category code " + asset.getCategoryCode() + " not found"));
        }

        List<Asset> assets = new ArrayList<Asset>();

        assets.add(asset);
        category.setAssets(assets);
        log.info("Category has {} assets", category.getAssets().size());

        assetService.addAsset(asset);
        return  ResponseEntity.ok().body(new MessageResponse("Asset added to the category with code " + asset.getCategoryCode() + " successfully"));
    }

    // Method      PUT
    // Description  Endpoint to update an asset via the asset code
    // Access    Private
    @PutMapping("{assetCode}")
    public ResponseEntity <?> updateAsset(@PathVariable String assetCode, @RequestBody Asset asset){
        Asset assetToUpdate = assetService.getAssetById(assetCode);

        if (assetToUpdate == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }

        if(assetToUpdate.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }

        Category category = categoryService.getCategory(asset.getCategoryCode());

        if (category == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Category not found"));
        }

        if (category.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Category not found"));
        }

        assetService.updateAsset(asset);

        return ResponseEntity.ok().body(asset);
    }

    // Method      DELETE
    // Description  Endpoint to delete an asset via the asset code
    // Access    Private
    @PutMapping("delete/{assetCode}")
    public  ResponseEntity<?> deleteAsset(@PathVariable String assetCode){
        Asset assetToDelete = assetService.getAssetById(assetCode);

        if (assetToDelete == null){
            return  ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }

        if (assetToDelete.getDeleteFlag() == 'Y'){
            return  ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }
        assetToDelete.setDeleteFlag('Y');

        assetService.deleteAsset(assetToDelete);

        return ResponseEntity.ok().body(new MessageResponse("Asset with the code "+ assetCode + " removed successfully"));
    }

    // Method      PUT
    // Description  Endpoint to perform asset revaluation
    // Access    Private
    @PutMapping("{assetCode}/revaluate-asset")
    public ResponseEntity<?> revaluateAsset(@PathVariable String assetCode, @RequestParam Double assetValue){
        // Check if asset exists
        Asset assetExists = assetService.getAssetById(assetCode);

        if(assetExists == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code " + assetCode + " not found"));
        }

        if(assetExists.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the code " + assetCode + " not found"));
        }

        assetExists.setValue(assetValue);
        assetExists.setDepreciationType("Reducing Balance Method");

        assetService.updateAsset(assetExists);

        return ResponseEntity.ok().body(assetExists);
    }

    // Method      PUT
    // Description  Endpoint for asset write off
    // Access    Private
    @PutMapping("{assetCode}/asset-write-off")
    public ResponseEntity<?> assetWriteOff(@PathVariable String assetCode, @RequestParam Double assetValue){
        Asset assetToWriteOff = assetService.getAssetById(assetCode);

        if(assetToWriteOff == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the id" + assetCode + " not found"));
        }

        if(assetToWriteOff.getDeleteFlag() == 'Y'){
            return  ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }
        assetToWriteOff.setValue(assetValue);
        assetToWriteOff.setDepreciationType("Reducing Balance Method");

        assetService.updateAsset(assetToWriteOff);

        return ResponseEntity.ok().body(assetToWriteOff);
    }

    // Method      PUT
    // Description  Endpoint for asset write off
    // Access    Private
    @PutMapping("{assetCode}/asset-dispose")
    public ResponseEntity<?> assetDispose(@PathVariable String assetCode, @RequestParam Double assetValue){
        Asset assetToWriteOff = assetService.getAssetById(assetCode);

        if(assetToWriteOff == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset with the id" + assetCode + " not found"));
        }

        if(assetToWriteOff.getDeleteFlag() == 'Y'){
            return  ResponseEntity.badRequest().body(new MessageResponse("Asset with the code "+ assetCode + " not found"));
        }
        assetToWriteOff.setValue(assetValue);
        assetToWriteOff.setDepreciationType("Reducing Balance Method");

        assetService.updateAsset(assetToWriteOff);

        return ResponseEntity.ok().body(assetToWriteOff);
    }


}
