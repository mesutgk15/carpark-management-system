package project.carparkmanagement.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import project.carparkmanagement.admin.mapper.Resident2ResidentDTO;
import project.carparkmanagement.admin.mapper.ResidentDTO2Resident;
import project.carparkmanagement.admin.model.dto.ResidentDTO;
import project.carparkmanagement.admin.model.entity.Resident;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.repository.ResidentRepository;
import project.carparkmanagement.admin.service.ResidentService;
import project.carparkmanagement.admin.service.VehicleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentDTO2Resident residentDTO2Resident;
    private final Resident2ResidentDTO resident2ResidentDTO;

    @Override
    public List<ResidentDTO> findAll() {
        List<Resident> residents = residentRepository.findAll();
        return residents.stream().map(r -> resident2ResidentDTO.map(r)).collect(Collectors.toList());
    }

    @Override
    public Resident findById(long id) {
        return residentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Resident.class, "Resident Not Found"));
    }

    @Override
    public ResidentDTO save(ResidentDTO residentDTO) {
        return resident2ResidentDTO.map(residentRepository.save(residentDTO2Resident.map(residentDTO)));
    }

    @Override
    public void delete(long id) {
        residentRepository.delete(findById(id));
    }

    @Override
    public Set<Vehicle> removeVehicle (Resident resident, Vehicle vehicle) {
        Set<Vehicle> vehicles = resident.getVehicles();
//        if (vehicle.getOwner() == null || !vehicle.getOwner().equals(resident) || vehicles == null) {
//            System.out.println("Incorrect owner for the vehicle");
//            return vehicles;
//        }
//        vehicle.setAuthorizedToGetIn(false);
//        if (vehicles.remove(vehicle)) {
//            System.out.printf("-Vehicle (%s) removed from Resident's (%s) Vehicle list\n", vehicle.getPlateNumber(), vehicle.getOwner().getName());
//        } else {
//            System.out.printf("-Vehicle (%s) was not found in Resident's (%s) Vehicle list\n", vehicle.getPlateNumber(), vehicle.getOwner().getName());
//        }
//
//        return vehicles;

        if (vehicles != null)
            vehicles.remove(vehicle);
        return vehicles;
    }
//    public Set<Vehicle> addVehicle (long residentId, long vehicleId) {
//        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new ObjectNotFoundException(Resident.class, "Resident"));
//        Vehicle vehicle = vehicleService.findById(vehicleId);
//
//        Set<Vehicle> vehicles = resident.getVehicles();
//
//        // Returns success status, collection is a hashSet
//        if (!vehicle.getOwner().equals(resident)) {
//            System.out.println("Owner of the car is not matching, set the owner of the correctly first");
//            return vehicles;
//        }
//
//        if(resident.getVehicles().add(vehicle)) {
//            residentRepository.save(resident);
//            return true;
//        } else {
//            return false;

//        }

//    }

    @Override
    public List<Vehicle> getVehicles(long id) {
        return new ArrayList<Vehicle>(findById(id).getVehicles());
    }
}
