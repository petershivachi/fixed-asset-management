package com.fixed.assets.app.fixedassets.Models.Custodian;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Custodian implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 50)
  private String custodianName;
  @Column(nullable = false, length = 50)
  private String email;
  @Column(nullable = false, length = 13)
  private String phone;
  @Column(nullable = false)
  private String category;
  private String description;
  private String createdBy;
  @Column(nullable = false)
  private String createdAt;
  private String modifiedBy;
  private String modifiedAt;

  public Custodian() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCustodianName() {
    return custodianName;
  }

  public void setCustodianName(String custodianName) {
    this.custodianName = custodianName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public String getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(String modifiedAt) {
    this.modifiedAt = modifiedAt;
  }
}
