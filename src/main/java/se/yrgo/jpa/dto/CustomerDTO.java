package se.yrgo.jpa.dto;

import se.yrgo.jpa.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    List<VehicleDTO> vehicles;


    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
