package se.yrgo.jpa.rest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.jpa.data.CustomerRepository;
import se.yrgo.jpa.data.VehicleRepository;
import se.yrgo.jpa.domain.Customer;
import se.yrgo.jpa.domain.Vehicle;
import se.yrgo.jpa.dto.CustomerDTO;
import se.yrgo.jpa.dto.VehicleDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleRestController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestParam String name, @RequestParam String phone) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phone);
        customerRepository.save(customer);
        return ResponseEntity.ok("Customer created successfully");
    }

    @PostMapping("/vehicles")
    public ResponseEntity<String> createVehicle(@RequestParam String registrationNumber,
                                                @RequestParam String brand, @RequestParam String model,
                                                @RequestParam int productionYear) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setProductionYear(productionYear);
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Vehicle created successfully");
    }

    @PostMapping("/customer/{customerId}/vehicles/{vehiclesId}")
    public ResponseEntity<String> connectVehicleToCustomer(@PathVariable long customerId, @PathVariable long vehiclesId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        Vehicle vehicle = vehicleRepository.findById(vehiclesId).orElseThrow();
        vehicle.setCustomer(customer);
        vehicleRepository.save(vehicle);
        return ResponseEntity.ok("Vehicle connected to customer successfully");
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomersWithVehicles() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO cdto = new CustomerDTO();
            cdto.setId(customer.getId());
            cdto.setName(customer.getName());
            cdto.setPhoneNumber(customer.getPhoneNumber());

            List<VehicleDTO> vehicleDTOs = new ArrayList<>();
            for (Vehicle vehicle : customer.getVehicles()) {
                vehicleDTOs.add(vehicleDTOConverter(vehicle));
            }
            cdto.setVehicles(vehicleDTOs);
            customerDTOs.add(cdto);
        }
        return customerDTOs;
    }

    @GetMapping("/vehicles")
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            VehicleDTO vehicleDTO = vehicleDTOConverter(vehicle);
            vehicleDTOs.add(vehicleDTO);
        }
        return vehicleDTOs;
    }

    @GetMapping("/vehicles_by_brand")
    public ResponseEntity<List<VehicleDTO>> getAllVehiclesByBrand(@RequestParam String brand) {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            vehicleDTOs.add(vehicleDTOConverter(vehicle));
        }
        return ResponseEntity.ok(vehicleDTOs);
    }

    @GetMapping("/customer-id")
    public ResponseEntity<Long> getCustomerIdByName(@RequestParam String name) {
        Customer customer = customerRepository.findByName(name);
                if ( customer != null ) {
                    return ResponseEntity.ok(customer.getId());
                } else {
                return ResponseEntity.notFound().build();
                }
    }

    @DeleteMapping("/clear-database")
    @Transactional
    public ResponseEntity<String> clearDatabase() {
        vehicleRepository.deleteAll();
        customerRepository.deleteAll();
        entityManager.createNativeQuery("ALTER TABLE customer ALTER COLUMN id RESTART WITH 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE vehicle ALTER COLUMN id RESTART WITH 1").executeUpdate();
        return ResponseEntity.ok("Database has been cleared successfully and IDs reset to 1.");
    }

    //Utility method
    private VehicleDTO vehicleDTOConverter(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setRegistrationNumber(vehicle.getRegistrationNumber());
        vehicleDTO.setBrand(vehicle.getBrand());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setProductionYear(vehicle.getProductionYear());
        return vehicleDTO;
    }

}
