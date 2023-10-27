package project.carparkmanagement.admin.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.carparkmanagement.admin.model.entity.Vehicle;


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
