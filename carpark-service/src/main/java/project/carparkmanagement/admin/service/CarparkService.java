package project.carparkmanagement.admin.service;

import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.model.entity.Carpark;

public interface CarparkService {

    CarparkDTO save(String carparkId, String name, int capacity);
    CarparkDTO save(CarparkDTO carparkDTO);

    void delete(long id);

    Carpark findById (long id);

    void enterVehicle(long carparkId, long vehicleId);
    void exitVehicle (long carparkId, long vehicleId);
}
