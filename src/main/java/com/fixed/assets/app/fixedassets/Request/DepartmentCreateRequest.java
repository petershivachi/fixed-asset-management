package com.fixed.assets.app.fixedassets.Request;

import javax.validation.constraints.NotBlank;

public class DepartmentCreateRequest {
  @NotBlank
  private String departmentName;
  @NotBlank
  private String departmentCode;

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }
}
