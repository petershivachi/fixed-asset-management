package com.fixed.assets.app.fixedassets.Models.Locations;

import com.fixed.assets.app.fixedassets.Request.LocationCreateRequest;
import com.fixed.assets.app.fixedassets.Request.LocationUpdateRequest;
import com.fixed.assets.app.fixedassets.Responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LocationService {
    @Autowired
    LocationRepository locationRepository;

  public ResponseEntity<?> addLocation (LocationCreateRequest locationCreateRequest) {
    Location location = new Location();
    location.setLocation(locationCreateRequest.getLocation());
    location.setCoordinates(locationCreateRequest.getCoordinates());
    location.setLocationCode(locationCreateRequest.getLocationCode());
    location.setDeleteFlag('N');
    location.setRcre(new Date());

    return ResponseEntity.ok().body( locationRepository.save(location));
  }

  public ResponseEntity<List<Location>> listAllALocations(){
    return ResponseEntity.ok().body(locationRepository.findByDeleteFlag('N'));
  }

  public ResponseEntity<?> listLocationByCode(String locationCode){
    Location location = locationRepository.findByLocationCode(locationCode);

    if(location == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Location with the code " + locationCode + " not found"));
    }

    if(location.getDeleteFlag() != 'N'){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Location with the code " + locationCode + " not found"));
    }

    return  ResponseEntity.ok().body(location);
  }

  public ResponseEntity<?> updateLocation(LocationUpdateRequest locationUpdateRequest, String locationCode){
    Location location = locationRepository.findByLocationCode(locationCode);

    if(location == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Location with the code " + locationCode + " not found"));
    }
    location.setLocation(locationUpdateRequest.getLocation());
    location.setCoordinates(locationUpdateRequest.getCoordinates());

    locationRepository.save(location);

    return ResponseEntity.ok().body(location);
  }

  public ResponseEntity<?> deleteLocation(String locationCode){
    Location location = locationRepository.findByLocationCode(locationCode);

    if(location == null){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Location with the code " + locationCode + " not found"));
    }

    if (location.getDeleteFlag() != 'N'){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Location with the code " + locationCode + " not found"));
    }

    location.setDeleteFlag('Y');
    locationRepository.save(location);

    return ResponseEntity.ok().body(location);
  }
}
