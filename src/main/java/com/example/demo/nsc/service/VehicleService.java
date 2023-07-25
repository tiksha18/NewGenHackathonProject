package com.example.demo.nsc.service;

import com.example.demo.nsc.entity.Vehicle;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface VehicleService {

    List<Vehicle> recommendNearestVehicles(Integer locationId, Integer noOfPassenger, String vehicleCategory);

    String addVehicles(Vehicle vehicle);
}
