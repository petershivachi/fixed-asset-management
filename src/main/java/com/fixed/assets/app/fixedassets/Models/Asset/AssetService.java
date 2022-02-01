package com.fixed.assets.app.fixedassets.Models.Asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public List<Asset> getAllAssets(){
        return assetRepository.findByDeleteFlag('N');
    }

    public Asset getAssetById(String assetId){
        return assetRepository.findByAssetCode(assetId);
    }

    public void addAsset(Asset asset){
        assetRepository.save(asset);
    }

    public void updateAsset(Asset asset){
        assetRepository.save(asset);
    }

   public void deleteAsset(Asset asset){
        assetRepository.save(asset);
   }

}
