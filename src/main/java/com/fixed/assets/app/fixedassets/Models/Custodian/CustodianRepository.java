package com.fixed.assets.app.fixedassets.Models.Custodian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustodianRepository extends JpaRepository<Custodian, Long> {
}
