package project.carparkmanagement.gateservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carparkmanagement.gateservice.model.GateLog;

@Repository
public interface GateLogRepository extends JpaRepository<GateLog, Long> {
}
