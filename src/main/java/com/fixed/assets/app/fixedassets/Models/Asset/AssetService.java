package com.fixed.assets.app.fixedassets.Models.Asset;

import com.fixed.assets.app.fixedassets.Utils.AssetCodeGeneratorUtil;
import com.fixed.assets.app.fixedassets.Utils.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    AssetCodeGeneratorUtil assetCodeGeneratorUtil;

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

   public void uploadExcelAssets(MultipartFile file, String categoryCode){
       try {
           List<Asset> assets = Excel.excelToAssets(file.getInputStream());
           List <Asset> myAssets = new ArrayList<Asset>();
               assets.forEach(asset -> {
                   asset.setCategoryCode(categoryCode);
                   asset.setRcre(new Date());
                   asset.setDeleteFlag('N');
                   asset.setAssetCode(assetCodeGeneratorUtil.generateAssetCode());
                   System.out.println("trial"+asset.getCost());
                   myAssets.add(asset);
               });

               assetRepository.saveAll(assets);
       }catch (IOException e){
           throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
       }
   }

}