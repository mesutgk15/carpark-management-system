package project.carparkmanagement.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.admin.model.entity.Carpark;

@Repository
public interface CarparkRepository extends JpaRepository<Carpark, Long> {

    Carpark findByName(String name);
}
