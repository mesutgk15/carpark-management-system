package project.carparkmanagement.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.carparkmanagement.admin.mapper.Carpark2CarparkDTO;
import project.carparkmanagement.admin.mapper.CarparkDTO2Carpark;
import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.model.entity.Carpark;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.repository.CarparkRepository;
import project.carparkmanagement.admin.service.CarparkService;
import project.carparkmanagement.admin.service.VehicleService;

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

    public void enterVehicle(long carparkId, long vehicleId) {
        Carpark carpark = findById(carparkId);
        Vehicle vehicle = vehicleService.getVehicleRepository().findById(vehicleId).orElseThrow(() -> new ObjectNotFoundException(Vehicle.class, "Vehicle"));

        Set<Vehicle> vehiclesInside = carpark.getVehiclesInside();
        Set<Vehicle> vehiclesInsideConsumesSpot = carpark.getVehiclesInsideConsumesSpace();
        System.out.printf("Enter initiated: %s\n", vehicle.getPlateNumber());
        if (!vehicle.isAuthorizedToGetIn()) {
            System.out.printf("Unauthorized vehicle (%s)\n", vehicle.getPlateNumber());
            return;
        }

        if (vehiclesInsideConsumesSpot.size() >= carpark.getCapacity()) {
            System.out.println("Out of Capacity");
            return;
        }

        if (vehiclesInside.contains(vehicle)) {
            System.out.printf("Misuse: Vehicle already inside (%s)\n", vehicle.getPlateNumber());
            return;
        }

        if (vehicle.isConsumesSpace()) {
            // Get the number of vehicles inside carpark belongs to requester vehicle's owner
            int vehiclesOfResidentInside = carpark.getVehiclesInsideConsumesSpace(vehicle.getOwner()).size();

            //Check if requester vehicle's apartment unit exceeds its carpark allowance, if so block entrance
            if (vehiclesOfResidentInside >= vehicle.getOwner().getCarparkAllowance()) {
                System.out.printf("Out of space for Resident: (%s)\n", vehicle.getOwner().getApartmentNumber());
                return;
            }

            vehiclesInsideConsumesSpot.add(vehicle);
        }

        vehiclesInside.add(vehicle);
        carparkRepository.save(carpark);
        System.out.println("Gate Opening...");


    }

    public void exitVehicle (long carparkId, long vehicleId) {
        Carpark carpark = findById(carparkId);
        Vehicle vehicle = vehicleService.getVehicleRepository().findById(vehicleId).orElseThrow(() -> new ObjectNotFoundException(Vehicle.class, "Vehicle"));
        Set<Vehicle> vehiclesInside = carpark.getVehiclesInside();
        Set<Vehicle> vehiclesInsideConsumesSpot = carpark.getVehiclesInsideConsumesSpace();
        System.out.printf("Exit initiated: %s\n", vehicle.getPlateNumber());
        if (!vehiclesInside.contains(vehicle)) {
            System.out.printf("Misuse: Vehicle already outside (%s)\n", vehicle.getPlateNumber());
            return;
        }

        System.out.println("Gate Opening...");
        vehiclesInside.remove(vehicle);
        vehiclesInsideConsumesSpot.remove(vehicle);

    }


}


