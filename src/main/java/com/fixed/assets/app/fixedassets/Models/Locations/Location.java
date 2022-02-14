package com.fixed.assets.app.fixedassets.Models.Locations;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Location implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false, length = 100)
  private String location;
  private String coordinates;
  @Column(nullable = false, length = 11, unique = true)
  private String locationCode;
  @Column(nullable = false)
  private Date rcre;
  @Column(nullable = false, length = 1)
  private Character deleteFlag;

  public Location() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(String coordinates) {
    this.coordinates = coordinates;
  }

  public String getLocationCode() {
    return locationCode;
  }

  public void setLocationCode(String locationCode) {
    this.locationCode = locationCode;
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
