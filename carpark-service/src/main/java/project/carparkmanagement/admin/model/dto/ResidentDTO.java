package project.carparkmanagement.admin.model.dto;

import lombok.*;
import project.carparkmanagement.admin.model.entity.Vehicle;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResidentDTO {
    private long id;
    private String name;
    private String contactNumber;
    private String apartmentNumber;
    private Integer carparkAllowance;
    private Set<VehicleDTO> vehicles;

}
