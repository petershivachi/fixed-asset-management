package com.fixed.assets.app.fixedassets.Models.Locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
  Location findByLocationCode(String locationCode);

  List<Location> findByDeleteFlag(Character deleteFlag);
}
