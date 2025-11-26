package se.yrgo.jpa.data;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.jpa.domain.Customer;
import se.yrgo.jpa.domain.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.vehicles")
    List<Customer> findALLCustomersWithVehicles();

}
