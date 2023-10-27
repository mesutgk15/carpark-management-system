package project.carparkmanagement.admin.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.carparkmanagement.admin.model.dto.CarDTO;
import project.carparkmanagement.admin.model.dto.MotorbikeDTO;
import project.carparkmanagement.admin.model.dto.ResidentDTO;
import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Car;
import project.carparkmanagement.admin.model.entity.Motorbike;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.service.ResidentService;
import project.carparkmanagement.admin.service.VehicleService;


@Component
@RequiredArgsConstructor
public class VehicleDTO2Vehicle implements BaseMapper<Vehicle, VehicleDTO> {

    private final VehicleService vehicleService;
    private final ResidentDTO2Resident residentDTO2Resident;
    private final ResidentService residentService;

    @Override
    public Vehicle map(VehicleDTO vehicleDTO, Object... params) {

        Vehicle vehicle = null;

        switch ((String) vehicleDTO.getClass().getSimpleName()) {
            case "MotorbikeDTO":
                vehicle = new Motorbike();
                break;
            default:
                vehicle = new Car();
                break;
        }


        long id = vehicleDTO.getId();
        String plateNumner = vehicleDTO.getPlateNumber();
        Vehicle.FuelType fuelType = vehicleDTO.getFuelType();
        Boolean authorizedToGetIn = vehicleDTO.getAuthorizedToGetIn();
        boolean consumesSpace = vehicleDTO.isConsumesSpace();
        Long ownerID = vehicleDTO.getOwnerID();

        if (id != 0)
            vehicle = vehicleService.findById(id);
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
