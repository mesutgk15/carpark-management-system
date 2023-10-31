package project.carparkmanagement.gateservice.service;

import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;
import project.carparkmanagement.gateservice.model.GateLog;

public interface GateService {

    VehicleEntryResponse enterVehicle(VehicleEntryRequest vehicleEntryRequest);
    VehicleEntryResponse exitVehicle(VehicleEntryRequest vehicleEntryRequest);
}
