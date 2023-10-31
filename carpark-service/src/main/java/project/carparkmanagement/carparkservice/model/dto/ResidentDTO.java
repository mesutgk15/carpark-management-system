package project.carparkmanagement.carparkservice.model.dto;

import lombok.*;

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
