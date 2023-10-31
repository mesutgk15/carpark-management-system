package project.carparkmanagement.carparkservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class CarDTO extends VehicleDTO {

    public CarDTO() {
        super();
        this.setConsumesSpace(true);
        this.setVehicleType(VEHICLE_TYPE.CAR);
    }

}
