package com.fixed.assets.app.fixedassets.Models.Depreciation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepreciationService {
    @Autowired
    private DepreciationRepository depreciationRepository;

    public List<Depreciation> listAllDepreciation(){
        return depreciationRepository.findDepreciationByDeleteFlag('N');
    }

    public Depreciation listDepreciationByCode(String depreciationCode){
        return depreciationRepository.findByDepreciationCode(depreciationCode);
    }

    public void addDepreciation(Depreciation depreciation){
        depreciationRepository.save(depreciation);
    }

    public void updateDepreciation(Depreciation depreciation){
        depreciationRepository.save(depreciation);
    }
}
