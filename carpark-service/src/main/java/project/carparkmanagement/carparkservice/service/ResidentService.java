package project.carparkmanagement.carparkservice.service;

import project.carparkmanagement.carparkservice.model.dto.ResidentDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Resident;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;

import java.util.List;
import java.util.Set;

public interface ResidentService {

    ResidentDTO save(ResidentDTO residentDTO);

    void delete(long id);
    Set<Vehicle> removeVehicle (Resident resident, Vehicle vehicle);
//    Set<Vehicle> addVehicle (long residentId, long vehicleId);
    Resident findById(long id);
    List<ResidentDTO> findAll();

    List<VehicleDTO> getVehicles(long id);
}
