package com.fixed.assets.app.fixedassets.Request;

import javax.validation.constraints.NotBlank;

public class LocationCreateRequest {
  @NotBlank
  private String location;

  @NotBlank
  private String  coordinates;

  @NotBlank
  private String locationCode;

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
}
