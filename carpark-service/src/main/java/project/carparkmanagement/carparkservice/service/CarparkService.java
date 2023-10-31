package project.carparkmanagement.carparkservice.service;

import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;
import project.carparkmanagement.carparkservice.model.dto.CarparkDTO;
import project.carparkmanagement.carparkservice.model.entity.Carpark;

public interface CarparkService {

    CarparkDTO save(String carparkId, String name, int capacity);
    CarparkDTO save(CarparkDTO carparkDTO);

    void delete(long id);

    Carpark findById (long id);

    VehicleEntryResponse enterVehicle(VehicleEntryRequest vehicleEntryRequest);
    VehicleEntryResponse exitVehicle (VehicleEntryRequest vehicleEntryRequest);

    Carpark findByCarparkID(String carparkID);


}
