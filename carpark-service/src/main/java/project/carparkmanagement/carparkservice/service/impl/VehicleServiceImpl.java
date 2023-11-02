package project.carparkmanagement.carparkservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.carparkmanagement.carparkservice.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Resident;
import project.carparkmanagement.carparkservice.repository.VehicleRepository;
import project.carparkmanagement.carparkservice.mapper.VehicleDTO2Vehicle;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.VehicleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class VehicleServiceImpl implements VehicleService {

    private final ResidentServiceImpl residentService;
    private final VehicleRepository vehicleRepository;
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;
    @Lazy
    private final VehicleDTO2Vehicle vehicleDTO2Vehicle;

    @Override
    public List<VehicleDTO> findAll() {
        return vehicleRepository.findAll().stream().map(v ->
                        vehicle2VehicleDTO.map(v))
                .collect(Collectors.toList());
    }

    @Override
    public Vehicle findById(long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Vehicle.class, "Vehicle Not Found"));
    }

    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleDTO2Vehicle.map(vehicleDTO);
        return vehicle2VehicleDTO.map(vehicleRepository.save(vehicle));
    }

    @Override
    public void delete(long id) {
        vehicleRepository.delete(findById(id));
    }

    @Override
    public Vehicle findByPlateNumber(String plateNumber) {
        return vehicleRepository.findByPlateNumber(plateNumber);
    }

    @Override
    public VehicleDTO setOwner(String plateNumber, long residentId) {

        Vehicle vehicle = findByPlateNumber(plateNumber);
        Resident owner = null;
        if (residentId != 0)
            owner = residentService.findById(residentId);

        vehicle.setOwner(owner);
        System.out.printf("-Owner set for Vehicle (%s)\n", vehicle.getPlateNumber());

        vehicleRepository.save(vehicle);
        return vehicle2VehicleDTO.map(vehicle);
    }

    @Override
    public VehicleDTO setAuthorizedToGetIn(String plateNumber, boolean authorizationToGetIn) {
        Vehicle vehicle = findByPlateNumber(plateNumber);
        vehicle.setAuthorizedToGetIn(authorizationToGetIn);
        vehicleRepository.save(vehicle);

        return vehicle2VehicleDTO.map(vehicle);

    }

}
