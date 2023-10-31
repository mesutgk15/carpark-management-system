package project.carparkmanagement.carparkservice.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Car;
import project.carparkmanagement.carparkservice.model.entity.Motorbike;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.ResidentService;
import project.carparkmanagement.carparkservice.service.VehicleService;


@Component
@RequiredArgsConstructor
public class VehicleDTO2Vehicle implements BaseMapper<Vehicle, VehicleDTO> {

    private final VehicleService vehicleService;
    private final ResidentDTO2Resident residentDTO2Resident;
    private final ResidentService residentService;

    @Override
    public Vehicle map(VehicleDTO vehicleDTO, Object... params) {

        Vehicle vehicle = null;

        long id = vehicleDTO.getId();

        if (id != 0)
            vehicle = vehicleService.findById(id);
        else {
            switch ((String) vehicleDTO.getClass().getSimpleName()) {
                case "MotorbikeDTO":
                    vehicle = new Motorbike();
                    break;
                default:
                    vehicle = new Car();
                    break;
            }
        }


        String plateNumner = vehicleDTO.getPlateNumber();
        Vehicle.FuelType fuelType = vehicleDTO.getFuelType();
        Boolean authorizedToGetIn = vehicleDTO.getAuthorizedToGetIn();
        boolean consumesSpace = vehicleDTO.isConsumesSpace();
        Long ownerID = vehicleDTO.getOwnerID();


        if (plateNumner != null)
            vehicle.setPlateNumber(plateNumner);
        if (fuelType != null)
            vehicle.setFuelType(fuelType);
        if (authorizedToGetIn != null)
            vehicle.setAuthorizedToGetIn(authorizedToGetIn);
        if (ownerID != null)
            vehicle.setOwner(residentService.findById(ownerID));
        vehicle.setConsumesSpace(consumesSpace);

        return vehicle;
    }
}
