package project.carparkmanagement.admin.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.carparkmanagement.admin.model.dto.CarDTO;
import project.carparkmanagement.admin.model.dto.MotorbikeDTO;
import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Vehicle;

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
        vehicleDTO.setOwnerID((vehicle.getOwner() != null) ? vehicle.getOwner().getId() : null);

        return vehicleDTO;
    }
}
