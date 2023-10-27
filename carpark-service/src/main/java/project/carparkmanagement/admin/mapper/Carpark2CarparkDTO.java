package project.carparkmanagement.admin.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.carparkmanagement.admin.model.dto.CarDTO;
import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.model.entity.Carpark;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class Carpark2CarparkDTO implements BaseMapper<CarparkDTO, Carpark>{

    private final Vehicle2VehicleDTO vehicle2VehicleDTO;

    @Override
    public CarparkDTO map(Carpark carpark, Object... params) {
        return CarparkDTO.builder()
                .id(carpark.getId())
                .name(carpark.getName())
                .carparkID(carpark.getCarparkID())
                .capacity(carpark.getCapacity())
                .vehiclesInside(carpark.getVehiclesInside().stream().map(v -> vehicle2VehicleDTO.map(v)).collect(Collectors.toSet()))
                .vehiclesInsideConsumesSpace(carpark.getVehiclesInsideConsumesSpace().stream().map(v -> vehicle2VehicleDTO.map(v)).collect(Collectors.toSet()))
                .build();
    }
}
