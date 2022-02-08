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
import java.util.concurrent.TimeUnit;

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
        double durationInMilliseconds = end.getTime() - start.getTime();

        // Duration in days
        double durationInDays = TimeUnit.DAYS.convert((long) durationInMilliseconds, TimeUnit.MILLISECONDS);

        // Duration in months
        double duration = Math.ceil(durationInDays / 30);

        // Find Depreciation to calculate
        Depreciation depreciationToCalculate = depreciationService.listDepreciationByCode(depreciation.getDepreciationCode());

        // Reducing Balance Method
        if (asset.getDepreciationType().equals("Reducing Balance Method")){
            double depreciationValue = Math.round((asset.getValue() * (asset.getDepreciationRate() / 100) * (duration / 12)) * 100) / 100.0;

            depreciationToCalculate.setDepreciation(depreciationValue);

            double newValue =Math.round((asset.getValue() + depreciationValue) * 100) / 100.0;

            depreciationToCalculate.setNewValue(newValue);
            depreciationToCalculate.setValue(asset.getValue());

            depreciationService.updateDepreciation(depreciationToCalculate);
        }else if(asset.getDepreciationType().equals("Straight Line Method")){
            // Straight Line Method
            double depreciationValue = Math.round((asset.getCost() * (asset.getDepreciationRate() / 100) * (duration / 12)) * 100) / 100.0;

            depreciationToCalculate.setDepreciation(depreciationValue);

            double newValue =Math.round((asset.getCost() + depreciationValue) * 100) / 100.0;

            depreciationToCalculate.setNewValue(newValue);

            depreciationService.updateDepreciation(depreciationToCalculate);
        } else {
            return  ResponseEntity.badRequest().body(new MessageResponse("The depreciation type you provided is not supported"));
        }

        return listDepreciationByCode(depreciation.getDepreciationCode());
    }
}
