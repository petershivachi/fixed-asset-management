package com.fixed.assets.app.fixedassets.Models.Departments;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Department implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 25)
  private String departmentName;
  @Column(nullable = false, length = 11)
  private String departmentCode;
  @Column(nullable = false)
  private Date rcre;
  @Column(nullable = false, length = 1)
  private Character deleteFlag;

  public Department() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Date getRcre() {
    return rcre;
  }

  public void setRcre(Date rcre) {
    this.rcre = rcre;
  }

  public Character getDeleteFlag() {
    return deleteFlag;
  }

  public void setDeleteFlag(Character deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
