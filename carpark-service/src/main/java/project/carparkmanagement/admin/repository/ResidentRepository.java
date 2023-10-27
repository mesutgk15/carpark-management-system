package project.carparkmanagement.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.admin.model.entity.Resident;


@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
