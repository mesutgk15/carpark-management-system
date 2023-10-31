package project.carparkmanagement.carparkservice.model.dto;

import lombok.*;

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
