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
        Resident owner = residentService.findById(residentId);


        //In case if the owner of the vehicle was already assigned to another resident,
        //remove it from its current owner
        if (vehicle.getOwner() != null) {
//             residentService.removeVehicle(vehicle.getOwner(), vehicle);
        }

        vehicle.setOwner(owner);
        System.out.printf("-Owner set for Vehicle (%s)\n", vehicle.getPlateNumber());
//        if (residentService.addVehicle(residentId, vehicleId))
//            System.out.printf("-Vehicle (%s) added to Resident's (%s) Vehicle list\n", vehicle.getPlateNumber(), owner.getName());
//        else
//            System.out.print("\t-It was already on Resident's Vehicle list or something went wrong\n");

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
