package com.example.demo.nsc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer locationId;
    private String vehicleNo;
    private String vehicleCategory;
    private Integer noOfPassengers;
    private String available;
    private String driverContactNo;

}
