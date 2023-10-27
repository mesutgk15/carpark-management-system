package project.carparkmanagement.admin.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.carparkmanagement.admin.model.dto.ResidentDTO;
import project.carparkmanagement.admin.model.entity.Resident;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class Resident2ResidentDTO implements BaseMapper<ResidentDTO, Resident> {

    @Lazy
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;
    @Override
    public ResidentDTO map(Resident resident, Object... params) {
        return ResidentDTO.builder()
                .id(resident.getId())
                .name(resident.getName())
                .apartmentNumber(resident.getApartmentNumber())
                .contactNumber(resident.getContactNumber())
                .carparkAllowance(resident.getCarparkAllowance())
                .vehicles(resident.getVehicles().stream().map(v ->
                        vehicle2VehicleDTO.map(v)).collect(Collectors.toSet()))
                .build();
    }
}
