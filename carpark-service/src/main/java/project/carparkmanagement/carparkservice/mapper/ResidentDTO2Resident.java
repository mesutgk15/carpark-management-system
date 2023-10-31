package project.carparkmanagement.carparkservice.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import project.carparkmanagement.carparkservice.model.dto.ResidentDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Resident;
import project.carparkmanagement.carparkservice.service.impl.ResidentServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ResidentDTO2Resident implements BaseMapper<Resident, ResidentDTO> {

    private final ResidentServiceImpl residentService;
    @Lazy
    private final VehicleDTO2Vehicle vehicleDTO2Vehicle;

    @Override
    public Resident map(ResidentDTO residentDTO, Object... params) {
        Resident resident = new Resident();

        long id = residentDTO.getId();
        String name = residentDTO.getName();
        String apartmentNumber = residentDTO.getApartmentNumber();
        String contactNumber = residentDTO.getContactNumber();
        Set<VehicleDTO> vehicles = residentDTO.getVehicles();
        Integer carparkAllowance = residentDTO.getCarparkAllowance();

        if (id != 0)
            resident = residentService.findById(id);
        if (name != null)
            resident.setName(name);
        if (apartmentNumber != null)
            resident.setApartmentNumber(apartmentNumber);
        if (contactNumber != null)
            resident.setContactNumber(contactNumber);
        if (vehicles != null && !vehicles.isEmpty())
            resident.setVehicles(vehicles.stream().map(v ->
                    vehicleDTO2Vehicle.map(v)).collect(Collectors.toSet()));
        if (carparkAllowance != null) {
            resident.setCarparkAllowance(carparkAllowance);
        }

        return resident;
    }
}
