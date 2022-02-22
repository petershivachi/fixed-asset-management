package com.fixed.assets.app.fixedassets.Models.Departments;

import com.fixed.assets.app.fixedassets.Request.DepartmentCreateRequest;
import com.fixed.assets.app.fixedassets.Request.DepartmentUpdateRequest;
import com.fixed.assets.app.fixedassets.Responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Service
public class DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;

  public ResponseEntity<?> addDepartment(DepartmentCreateRequest departmentCreateRequest){
    Department departmentExists = departmentRepository.findByDepartmentCode(departmentCreateRequest.getDepartmentCode());

    System.out.println("Testing for department code " + departmentCreateRequest.getDepartmentCode());
    System.out.println("Testing for department code " + departmentCreateRequest.getDepartmentName());

    if(departmentExists != null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department with the code " + departmentCreateRequest.getDepartmentCode() + "already exist");
    }

    Department department = new Department();
    department.setDepartmentName(departmentCreateRequest.getDepartmentName());
    department.setDepartmentCode(departmentCreateRequest.getDepartmentCode());
    department.setDeleteFlag('Y');
    department.setRcre(new Date());

    departmentRepository.save(department);

    return ResponseEntity.ok().body(department);
  }

  public ResponseEntity<?> listAllDepartments(){
    return ResponseEntity.ok().body(departmentRepository.findByDeleteFlag('N'));
  }

  public ResponseEntity<?> listDepartmentByCode(String departmentCode){
    Department department = departmentRepository.findByDepartmentCode(departmentCode);

    if(department == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Department with the code "+ departmentCode + " not found"));
    }

    if(department.getDeleteFlag() != 'N'){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Department with the code "+ departmentCode + "not found"));
    }

    return ResponseEntity.ok().body(department);
  }

  public ResponseEntity<?> updateDepartment(DepartmentUpdateRequest departmentUpdateRequest, String departmentCode){
    Department department = departmentRepository.findByDepartmentCode(departmentCode);

    if(department == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department with the code "+ departmentCode + "not found");
    }

    if(department.getDeleteFlag() != 'N'){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Department with the code "+ departmentCode + "not found");
    }

    department.setDepartmentName(departmentUpdateRequest.getDepartmentName());
    department.setDepartmentCode(departmentUpdateRequest.getDepartmentCode());

    departmentRepository.save(department);
    return ResponseEntity.ok().body(department);
  }

  public ResponseEntity<?> deleteDepartment(@PathVariable String departmentCode){
    Department department = departmentRepository.findByDepartmentCode(departmentCode);

    if(department == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Department with the code " + departmentCode + " not found"));
    }
    
    if(department.getDeleteFlag() != null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Department with the code " + departmentCode + " not found"));
    }
    
    department.setDeleteFlag('Y');

    departmentRepository.save(department);

    return ResponseEntity.ok().body(department);
  }
}
