package com.fixed.assets.app.fixedassets.Models.Depreciation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepreciationRepository extends JpaRepository<Depreciation, Long> {
}
