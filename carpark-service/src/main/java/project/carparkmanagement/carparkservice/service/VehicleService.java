package project.carparkmanagement.carparkservice.service;

import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    List<VehicleDTO> findAll();
    Vehicle findById(long id);
    VehicleDTO setOwner(String plateNumber, long residentId);
    VehicleDTO setAuthorizedToGetIn(String plateNumber, boolean authorizationToGetIn);

    VehicleDTO save(VehicleDTO vehicleDTO);
    void delete(long id);

    Vehicle findByPlateNumber(String plateNumber);



}
