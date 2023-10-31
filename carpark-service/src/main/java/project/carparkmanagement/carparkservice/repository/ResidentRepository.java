package project.carparkmanagement.carparkservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.carparkservice.model.entity.Resident;


@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
