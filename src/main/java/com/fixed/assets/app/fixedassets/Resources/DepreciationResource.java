package com.fixed.assets.app.fixedassets.Resources;

import com.fixed.assets.app.fixedassets.Models.Asset.Asset;
import com.fixed.assets.app.fixedassets.Models.Asset.AssetService;
import com.fixed.assets.app.fixedassets.Models.Depreciation.Depreciation;
import com.fixed.assets.app.fixedassets.Models.Depreciation.DepreciationService;
import com.fixed.assets.app.fixedassets.Responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/depreciation")
public class DepreciationResource {
    @Autowired
    private DepreciationService depreciationService;

    @Autowired
    private AssetService assetService;

    // Method      GET
    // Description Endpoint to get all depreciation
    // Access Private
    @GetMapping("list")
    public ResponseEntity<?> listAllDepreciation(){
        return ResponseEntity.ok().body(depreciationService.listAllDepreciation());
    }

    // Method      GET
    // Description Endpoint to get a depreciation by code
    // Access Private
    @GetMapping("{depreciationCode}")
    ResponseEntity<?> listDepreciationByCode(@PathVariable String depreciationCode){
        Depreciation depreciation = depreciationService.listDepreciationByCode(depreciationCode);

        if(depreciation == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Depreciation with the code " + depreciationCode + " not found"));
        }

        return ResponseEntity.ok().body(depreciation);
    }

    // Method      POST
    // Description Endpoint to add asset depreciation
    // Access Private
    @PostMapping("calculate-depreciation")
    ResponseEntity<?> addDepreciation(@RequestBody Depreciation depreciation){
        depreciation.setDeleteFlag('N');
        depreciation.setRcre(new Date());

        Asset assetExists = assetService.getAssetById(depreciation.getAssetCode());

        if(assetExists == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset not found"));
        }

        if (assetExists.getDeleteFlag() == 'Y'){
            return ResponseEntity.badRequest().body(new MessageResponse("Asset not found"));
        }

        if(assetExists.getDepreciation() == null){
            depreciation.setDepreciationType(assetExists.getDepreciationType());
            depreciation.setCost(assetExists.getCost());
            depreciation.setAssetName(assetExists.getName());

            assetExists.setDepreciation(depreciation);

            depreciationService.addDepreciation(depreciation);
        }else {
            Depreciation depreciationToUpdate = assetExists.getDepreciation();
            depreciationToUpdate.setStartDate(depreciation.getStartDate());
            depreciationToUpdate.setEndDate(depreciation.getEndDate());
            depreciationToUpdate.setDepreciationCode(depreciation.getDepreciationCode());

            assetExists.setDepreciation(depreciationToUpdate);
            depreciationService.updateDepreciation(depreciationToUpdate);
        }

        Asset asset = assetService.getAssetById(depreciation.getAssetCode());

        Date start = depreciation.getStartDate();
        Date end = depreciation.getEndDate();
        long durationInMilliseconds = end.getTime() - start.getTime();

        // Duration in days
        long durationInDays = (durationInMilliseconds / (1000 * 60 * 60 * 24)) / 365;

        // Duration in months
        long duration = durationInDays / 28;

        // Reducing Balance Method
        if (asset.getDepreciationType().equals("Reducing Balance Method")){
            Long depreciationValue = asset.getCost() * (asset.getDepreciationRate() / 100) * (duration / 12);

            depreciation.setDepreciation(depreciationValue);

            Long newValue = asset.getCost() - depreciationValue;

            depreciation.setNewValue(newValue);
        }else if(asset.getDepreciationType().equals("Straight Line Method")){
            Long depreciationValue = asset.getCost() * (asset.getDepreciationRate() / 100) * (duration / 12);

            depreciation.setDepreciation(depreciationValue);

            Long newValue = asset.getCost() - depreciationValue;

            depreciation.setNewValue(newValue);
        } else {
            return  ResponseEntity.badRequest().body(new MessageResponse("The depreciation type you provided does not exist"));
        }

        return listDepreciationByCode(depreciation.getDepreciationCode());
    }
}
