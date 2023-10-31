package project.carparkmanagement.carparkservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Vehicle findByPlateNumber(String plateNumber);
}
