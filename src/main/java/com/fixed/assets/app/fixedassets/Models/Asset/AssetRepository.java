package com.fixed.assets.app.fixedassets.Models.Asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
        Asset findByAssetCode(String assetCode);

        List<Asset> findByDeleteFlag(Character deleteFlag);
}
