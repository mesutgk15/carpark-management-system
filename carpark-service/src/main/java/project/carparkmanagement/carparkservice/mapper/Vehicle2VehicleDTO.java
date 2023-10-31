package project.carparkmanagement.carparkservice.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.carparkmanagement.carparkservice.model.dto.CarDTO;
import project.carparkmanagement.carparkservice.model.dto.MotorbikeDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;

@Component
@RequiredArgsConstructor
public class Vehicle2VehicleDTO implements BaseMapper<VehicleDTO, Vehicle> {

    private final Resident2ResidentDTO resident2ResidentDTO;

    @Override
    public VehicleDTO map(Vehicle vehicle, Object... params) {

        VehicleDTO vehicleDTO = null;

        switch ((String) vehicle.getClass().getSimpleName()) {
            case "Motorbike":
                vehicleDTO = new MotorbikeDTO();
                break;
            default:
                vehicleDTO = new CarDTO();
                break;
        }

        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setPlateNumber(vehicle.getPlateNumber());
        vehicleDTO.setFuelType(vehicle.getFuelType());
        vehicleDTO.setAuthorizedToGetIn(vehicle.isAuthorizedToGetIn());
        vehicleDTO.setConsumesSpace(vehicle.isConsumesSpace());
        vehicleDTO.setOwnerID((vehicle.getOwner() != null) ? vehicle.getOwner().getId() : null);

        return vehicleDTO;
    }
}
