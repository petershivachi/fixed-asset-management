package com.fixed.assets.app.fixedassets.Request;

public class DepartmentUpdateRequest {
  private String departmentName;
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