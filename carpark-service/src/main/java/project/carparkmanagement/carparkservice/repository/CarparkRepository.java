package project.carparkmanagement.carparkservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.carparkservice.model.entity.Carpark;

@Repository
public interface CarparkRepository extends JpaRepository<Carpark, Long> {

    Carpark findByName(String name);
    Carpark findByCarparkID(String carparkID);
}
