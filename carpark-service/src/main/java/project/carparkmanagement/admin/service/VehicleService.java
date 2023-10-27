package project.carparkmanagement.admin.service;

import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.repository.VehicleRepository;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();
    Vehicle findById(long id);
    VehicleDTO setOwner(long vehicleId, long residentId);
    VehicleDTO setAuthorizedToGetIn(long vehicleId, boolean authorizationToGetIn);

    VehicleDTO save(VehicleDTO vehicleDTO);
    void delete(long id);

    VehicleRepository getVehicleRepository();

}
