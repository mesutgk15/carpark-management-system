package project.carparkmanagement.admin.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import project.carparkmanagement.admin.mapper.VehicleDTO2Vehicle;
import project.carparkmanagement.admin.model.dto.ResidentDTO;
import project.carparkmanagement.admin.model.entity.Resident;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.repository.ResidentRepository;
import project.carparkmanagement.admin.service.impl.VehicleServiceImpl;

import java.util.List;
import java.util.Set;

public interface ResidentService {

    ResidentDTO save(ResidentDTO residentDTO);

    void delete(long id);
    Set<Vehicle> removeVehicle (Resident resident, Vehicle vehicle);
//    Set<Vehicle> addVehicle (long residentId, long vehicleId);
    Resident findById(long id);
    List<ResidentDTO> findAll();

    List<Vehicle> getVehicles(long id);
}
