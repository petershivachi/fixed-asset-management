package com.fixed.assets.app.fixedassets.Resources;

import com.fixed.assets.app.fixedassets.Models.Locations.LocationService;
import com.fixed.assets.app.fixedassets.Request.LocationCreateRequest;
import com.fixed.assets.app.fixedassets.Request.LocationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/locations")
public class LocationResource {
  @Autowired
  private LocationService locationService;

  // @desc   Add Location
  // @route  POST /api/v1/locations/add
  // access  Private
  @PostMapping("/add")
  public ResponseEntity<?> addLocation(@Valid @RequestBody LocationCreateRequest locationCreateRequest){
     return ResponseEntity.ok().body(locationService.addLocation(locationCreateRequest));
  }

  // @desc   List All locations
  // @route  GET /api/v1/locations/add
  // access  Private
  @GetMapping("/all")
  public ResponseEntity<?> listAllLocations(){
    return ResponseEntity.ok().body(locationService.listAllALocations());
  }

  // @desc   List location by code
  // @route  GET /api/v1/locations/{{locationCode}}
  // access  Private
  @GetMapping("{locationCode}")
  public ResponseEntity<?> listAssetByCode(@PathVariable String locationCode){
    return ResponseEntity.ok().body(locationService.listLocationByCode(locationCode));
  }

  // @desc   Update location
  // @route  PUT /api/v1/locations/{{locationCode}}
  // access  Private
  @PutMapping("{locationCode}")
  public ResponseEntity<?> updateAssetByCode(@RequestBody LocationUpdateRequest locationUpdateRequest, @PathVariable String locationCode){
    return ResponseEntity.ok().body(locationService.updateLocation(locationUpdateRequest, locationCode));
  }

  // @desc   Delete location
  // @route  PUT /api/v1/locations/delete/{{locationCode}}
  // access  Private
  @PutMapping("/delete/{locationCode}")
  public ResponseEntity<?> deleteLocation(@PathVariable String locationCode){
    return ResponseEntity.ok().body(locationService.deleteLocation(locationCode));
  }

}
