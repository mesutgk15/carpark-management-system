package project.carparkmanagement.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import project.carparkmanagement.admin.model.entity.Vehicle;

import javax.persistence.Inheritance;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "vehicleType", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = CarDTO.class, name = "CAR"),
        @JsonSubTypes.Type(value = MotorbikeDTO.class, name = "MOTORBIKE")})
public abstract class VehicleDTO {
    private long id;
    private String plateNumber;
    private Vehicle.FuelType fuelType;
    private Boolean authorizedToGetIn;
    private boolean consumesSpace;
    private Long ownerID;
    private VEHICLE_TYPE vehicleType;

    public enum VEHICLE_TYPE{
        CAR,
        MOTORBIKE
    }
}
