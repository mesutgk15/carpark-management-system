package project.carparkmanagement.admin.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Car;
import project.carparkmanagement.admin.model.entity.Carpark;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.service.CarparkService;

import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CarparkDTO2Carpark implements BaseMapper<Carpark, CarparkDTO>{

    private final CarparkService carparkService;
    private final VehicleDTO2Vehicle vehicleDTO2Vehicle;

    @Override
    public Carpark map(CarparkDTO carparkDTO, Object... params) {
        Carpark carpark = new Carpark();

        long id = carparkDTO.getId();
        String carparkID = carparkDTO.getCarparkID();
        Integer capacity = carparkDTO.getCapacity();
        String name = carparkDTO.getName();
        Set<VehicleDTO> vehiclesInside = carparkDTO.getVehiclesInside();
        Set<VehicleDTO> vehiclesInsideConsumesSpace = carparkDTO.getVehiclesInsideConsumesSpace();

        if (id != 0)
            carpark = carparkService.findById(id);
        if (carparkID != null)
            carpark.setCarparkID(carparkID);
        if (capacity != null)
            carpark.setCapacity(capacity);
        if (name != null)
            carpark.setName(name);
        if (vehiclesInside != null && !vehiclesInside.isEmpty())
            carpark.setVehiclesInside(vehiclesInside.stream().map(v -> vehicleDTO2Vehicle.map(v)).collect(Collectors.toSet()));
        if (vehiclesInside != null && !vehiclesInsideConsumesSpace.isEmpty())
            carpark.setVehiclesInsideConsumesSpace(vehiclesInsideConsumesSpace.stream().map(v -> vehicleDTO2Vehicle.map(v)).collect(Collectors.toSet()));

        return carpark;
    }
}
