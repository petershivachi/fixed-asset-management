package com.fixed.assets.app.fixedassets.Models.Depreciation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface DepreciationRepository extends JpaRepository<Depreciation, Long> {
    List<Depreciation> findDepreciationByDeleteFlag(Character deleteFlag);

    Depreciation findByDepreciationCode(String depreciationCode);

    @Transactional
    void deleteByDepreciationCode(String depreciationCode);
}
