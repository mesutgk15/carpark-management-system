package project.carparkmanagement.carparkservice.service.impl;

import project.carparkmanagement.carparkservice.mapper.CarparkDTO2Carpark;
import project.carparkmanagement.carparkservice.repository.CarparkRepository;
import project.carparkmanagement.carparkservice.service.CarparkService;
import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;
import project.carparkmanagement.client.carpark.enums.ReasonCodesGate;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.carparkmanagement.carparkservice.mapper.Carpark2CarparkDTO;
import project.carparkmanagement.carparkservice.model.dto.CarparkDTO;
import project.carparkmanagement.carparkservice.model.entity.Carpark;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.VehicleService;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class CarparkServiceImpl implements CarparkService {

    private final CarparkRepository carparkRepository;
    private final VehicleService vehicleService;
    private final Carpark2CarparkDTO carpark2CarparkDTO;
    @Lazy
    private final CarparkDTO2Carpark carparkDTO2Carpark;



    @Override
    public CarparkDTO save(String carparkId, String name, int capacity) {
        return save(CarparkDTO.builder()
                .carparkID(carparkId)
                .name(name)
                .capacity(capacity)
                .build());
    }

    @Override
    public CarparkDTO save(CarparkDTO carparkDTO) {
        Carpark carpark = carparkDTO2Carpark.map(carparkDTO);
        return carpark2CarparkDTO.map(carparkRepository.save(carpark));
    }

    @Override
    public void delete(long id) {
        carparkRepository.delete(findById(id));
    }


    @Override
    public Carpark findById(long id) {
        return carparkRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Carpark.class, "Carpark not found"));
    }

    @Override
    public Carpark findByCarparkID(String carparkID) {
        return carparkRepository.findByCarparkID(carparkID);
    }

    @Override
    public VehicleEntryResponse enterVehicle(VehicleEntryRequest vehicleEntryRequest) {
        Carpark carpark = findByCarparkID(vehicleEntryRequest.getCarparkID());
        Vehicle vehicle = vehicleService.findByPlateNumber(vehicleEntryRequest.getPlateNumber());

        VehicleEntryResponse vehicleEntryResponse = VehicleEntryResponse.builder()
                .vehiclePlate(vehicle.getPlateNumber())
                .carparkID(carpark.getCarparkID())
                .time(LocalDateTime.now())
                .status(false)
                .build();

        Set<Vehicle> vehiclesInside = carpark.getVehiclesInside();
        Set<Vehicle> vehiclesInsideConsumesSpot = carpark.getVehiclesInsideConsumesSpace();
        System.out.printf("Vehicle Identified for Entry: %s\n", vehicle.getPlateNumber());
        if (!vehicle.isAuthorizedToGetIn()) {
            vehicleEntryResponse.setReasonCode(ReasonCodesGate.UNAUTHORIZED_VEHICLE);
            return vehicleEntryResponse;
        }

        if (vehicle.getOwner() == null) {
            vehicleEntryResponse.setReasonCode(ReasonCodesGate.NO_OWNER_SET_FOR_VEHICLE);
            return vehicleEntryResponse;
        }

        if (vehiclesInsideConsumesSpot.size() >= carpark.getCapacity()) {
            vehicleEntryResponse.setReasonCode(ReasonCodesGate.OUT_OF_CAPACITY);
            return vehicleEntryResponse;
        }

        if (vehiclesInside.contains(vehicle)) {
            vehicleEntryResponse.setReasonCode(ReasonCodesGate.MISUSE_VEHICLE_ALREADY_INSIDE);
            return vehicleEntryResponse;
        }

        if (vehicle.isConsumesSpace()) {
            // Get the number of vehicles inside carpark belongs to requester vehicle's owner
            int vehiclesOfResidentInside = carpark.getVehiclesInsideConsumesSpace(vehicle.getOwner()).size();

            //Check if requester vehicle's apartment unit exceeds its carpark allowance, if so block entrance
            if (vehiclesOfResidentInside >= vehicle.getOwner().getCarparkAllowance()) {
                vehicleEntryResponse.setReasonCode(ReasonCodesGate.OUT_OF_SPACE_FOR_RESIDENT);
                return vehicleEntryResponse;
            }

            vehiclesInsideConsumesSpot.add(vehicle);
        }

        vehiclesInside.add(vehicle);

        carparkRepository.save(carpark);
        vehicleEntryResponse.setStatus(true);
        return vehicleEntryResponse;
    }

    @Override
    public VehicleEntryResponse exitVehicle (VehicleEntryRequest vehicleEntryRequest) {
        Carpark carpark = findByCarparkID(vehicleEntryRequest.getCarparkID());
        Vehicle vehicle = vehicleService.findByPlateNumber(vehicleEntryRequest.getPlateNumber());

        VehicleEntryResponse vehicleEntryResponse = VehicleEntryResponse.builder()
                .vehiclePlate(vehicle.getPlateNumber())
                .carparkID(carpark.getCarparkID())
                .time(LocalDateTime.now())
                .status(false)
                .build();

        Set<Vehicle> vehiclesInside = carpark.getVehiclesInside();
        Set<Vehicle> vehiclesInsideConsumesSpot = carpark.getVehiclesInsideConsumesSpace();

        if (!vehiclesInside.contains(vehicle)) {
            System.out.printf("Misuse: Vehicle already outside (%s)\n", vehicle.getPlateNumber());
            vehicleEntryResponse.setReasonCode(ReasonCodesGate.MISUSE_VEHICLE_ALREADY_OUTSIDE);
            return vehicleEntryResponse;
        }



        vehiclesInside.remove(vehicle);
        vehiclesInsideConsumesSpot.remove(vehicle);

        vehicleEntryResponse.setStatus(true);
        return vehicleEntryResponse;

    }




}


