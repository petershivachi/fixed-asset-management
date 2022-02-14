package com.fixed.assets.app.fixedassets.Models.Departments;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Department implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 25)
  private String departmentName;

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
}
