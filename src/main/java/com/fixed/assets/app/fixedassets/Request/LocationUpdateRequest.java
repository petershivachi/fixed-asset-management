package com.fixed.assets.app.fixedassets.Request;

public class LocationUpdateRequest {
  private String location;
  private String coordinates;

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
}
