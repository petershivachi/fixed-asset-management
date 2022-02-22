package com.fixed.assets.app.fixedassets.Resources;

import com.fixed.assets.app.fixedassets.Models.Departments.DepartmentService;
import com.fixed.assets.app.fixedassets.Request.DepartmentCreateRequest;
import com.fixed.assets.app.fixedassets.Request.DepartmentUpdateRequest;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/departments")
public class DepartmentResource {
  @Autowired
  private DepartmentService departmentService;

  // @desc   Add department
  // @route  POST /api/v1/departments/add
  // access  Private
  @PostMapping("/add")
  public ResponseEntity<?> addDepartment(@Valid  @RequestBody DepartmentCreateRequest departmentCreateRequest){
    return ResponseEntity.ok().body(departmentService.addDepartment(departmentCreateRequest));
  }

  // @desc   List all departments
  // @route  GET /api/v1/departments/all
  // access  Private
  @GetMapping("/all")
  public ResponseEntity<?> listAllDepartments(){
    return ResponseEntity.ok().body(departmentService.listAllDepartments());
  }

  // @desc   List department by code
  // @route  GET /api/v1/departments/{departmentCode}
  // access  Private
  @GetMapping("/{departmentCode}")
  public ResponseEntity<?> getDepartmentByCode(@PathVariable String departmentCode){
    return ResponseEntity.ok().body(departmentService.listDepartmentByCode(departmentCode));
  }

  // @desc   Update department via code
  // @route  PUT /api/v1/departments/{departmentCode}
  // access  Private
  @PutMapping("/{departmentCode}")
  public ResponseEntity<?> updateDepartment(@RequestBody DepartmentUpdateRequest departmentUpdateRequest, @PathVariable String departmentCode){
    return ResponseEntity.ok(departmentService.updateDepartment(departmentUpdateRequest, departmentCode));
  }

  // @desc   Update department via code
  // @route  PUT /api/v1/departments/{departmentCode}
  // access  Private
  @PutMapping("/delete/{departmentCode}")
  public ResponseEntity<?> deleteDepartment(@PathVariable String departmentCode){
    return ResponseEntity.ok().body(departmentService.deleteDepartment(departmentCode));
  }
}
