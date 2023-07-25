package com.example.demo.nsc.controller;

import com.example.demo.nsc.dto.MessageDto;
import com.example.demo.nsc.entity.Vehicle;
import com.example.demo.nsc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleRecommendation;

    @PostMapping("/add/vehicles")
    public ResponseEntity<?> addVehicles(@RequestBody Vehicle vehicle)
    {
        return new ResponseEntity<>(vehicleRecommendation.addVehicles(vehicle), HttpStatus.OK);
    }

    @GetMapping("/recommend/nearest/vehicles")
    public ResponseEntity<?> recommendVehicles(@RequestParam Integer locationId, @RequestParam Integer noOfPassenger, @RequestParam(required = false) String vehicleCategory)
    {
        try
        {
            List<Vehicle> vehicles = vehicleRecommendation.recommendNearestVehicles(locationId, noOfPassenger, vehicleCategory);
            if(vehicles.isEmpty())
            {
                return new ResponseEntity<>(new MessageDto("No vehicles are available currently"), HttpStatus.OK);
            }
            else {
                return ResponseEntity.ok(vehicles);
            }
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().build();
        }

    }

}
