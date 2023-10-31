package project.carparkmanagement.carparkservice.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.carparkmanagement.carparkservice.model.dto.ResidentDTO;
import project.carparkmanagement.carparkservice.model.entity.Resident;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class Resident2ResidentDTO implements BaseMapper<ResidentDTO, Resident> {

    @Lazy
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;
    @Override
    public ResidentDTO map(Resident resident, Object... params) {
        ResidentDTO residentDTO = ResidentDTO.builder()
                .id(resident.getId())
                .name(resident.getName())
                .apartmentNumber(resident.getApartmentNumber())
                .contactNumber(resident.getContactNumber())
                .carparkAllowance(resident.getCarparkAllowance())
                .build();

        if (resident.getVehicles() != null && !resident.getVehicles().isEmpty()) {
            residentDTO.setVehicles(resident.getVehicles().stream().map(v ->
                    vehicle2VehicleDTO.map(v)).collect(Collectors.toSet()));
        }
        return residentDTO;
    }
}
