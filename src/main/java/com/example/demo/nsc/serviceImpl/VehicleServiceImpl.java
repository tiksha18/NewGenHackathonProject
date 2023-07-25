package com.example.demo.nsc.serviceImpl;

import com.example.demo.nsc.entity.Vehicle;
import com.example.demo.nsc.exceptionHandler.AlreadyExistsException;
import com.example.demo.nsc.repository.VehicleRepository;
import com.example.demo.nsc.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public String addVehicles(Vehicle vehicleDto)
    {
        if(vehicleRepository.existsByVehicleNo(vehicleDto.getVehicleNo()))
        {
            throw new AlreadyExistsException("This Vehicle already exists");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setVehicleNo(vehicleDto.getVehicleNo());
        vehicle.setVehicleCategory(vehicleDto.getVehicleCategory());
        vehicle.setLocationId(vehicleDto.getLocationId());
        vehicle.setAvailable(vehicleDto.getAvailable());
        vehicle.setDriverContactNo(vehicleDto.getDriverContactNo());
        vehicle.setNoOfPassengers(vehicleDto.getNoOfPassengers());
        vehicleRepository.save(vehicle);
        return "Vehicle added";
    }

    @Override
    public List<Vehicle> recommendNearestVehicles(Integer locationId, Integer noOfPassenger, String vehicleCategory)
    {
        List<Vehicle> availableVehicles;
        if(vehicleCategory != null)
        {
            availableVehicles = vehicleRepository.findByAvailableTrueAndVehicleCategory(vehicleCategory);
        }
        else
        {
            availableVehicles = vehicleRepository.findByAvailableTrue();
        }
        availableVehicles.sort(Comparator.comparingInt(v -> calculateDistance(locationId, v.getLocationId())));
        return availableVehicles.stream()
                .filter(v -> v.getNoOfPassengers() >= noOfPassenger)
                .limit(3)
                .collect(Collectors.toList());
    }

    private int calculateDistance(Integer locationId1, Integer locationId2) {
        return (locationId1 - locationId2) * (locationId1 - locationId2);
    }
}
