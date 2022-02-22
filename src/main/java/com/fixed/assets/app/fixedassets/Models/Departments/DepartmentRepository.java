package com.fixed.assets.app.fixedassets.Models.Departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
  List<Department> findByDeleteFlag(Character deleteFlag);

  Department findByDepartmentCode(String departmentCode);
}
