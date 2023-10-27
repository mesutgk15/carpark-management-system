package project.carparkmanagement.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.carparkmanagement.admin.model.entity.Vehicle;

@Getter
@Setter
@Builder
public class MotorbikeDTO extends VehicleDTO {

    public MotorbikeDTO() {
        super();
        this.setConsumesSpace(false);
        this.setVehicleType(VEHICLE_TYPE.MOTORBIKE);
    }

}
