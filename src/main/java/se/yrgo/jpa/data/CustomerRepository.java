package se.yrgo.jpa.data;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import se.yrgo.jpa.domain.Customer;
import se.yrgo.jpa.domain.Vehicle;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Customer findByName(String name);

    @Query("SELECT v FROM Vehicle v  Where v.customer.id = :customerId")
    List<Vehicle> findVehiclesByCustomerId(@Param("customerId") Long customerId);
}
