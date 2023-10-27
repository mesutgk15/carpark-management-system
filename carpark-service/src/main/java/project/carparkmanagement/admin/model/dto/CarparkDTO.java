package project.carparkmanagement.admin.model.dto;

import lombok.*;
import project.carparkmanagement.admin.model.entity.Vehicle;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CarparkDTO {
    private long id;
    private String carparkID;
    private String name;
    private Integer capacity;
    private Set<VehicleDTO> vehiclesInside;
    private Set<VehicleDTO> vehiclesInsideConsumesSpace;


}
