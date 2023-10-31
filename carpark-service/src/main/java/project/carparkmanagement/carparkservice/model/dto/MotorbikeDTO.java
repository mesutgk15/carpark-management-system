package project.carparkmanagement.carparkservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
