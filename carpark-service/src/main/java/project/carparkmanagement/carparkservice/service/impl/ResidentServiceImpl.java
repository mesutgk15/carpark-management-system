package project.carparkmanagement.carparkservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import project.carparkmanagement.carparkservice.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.repository.ResidentRepository;
import project.carparkmanagement.carparkservice.mapper.Resident2ResidentDTO;
import project.carparkmanagement.carparkservice.mapper.ResidentDTO2Resident;
import project.carparkmanagement.carparkservice.model.dto.ResidentDTO;
import project.carparkmanagement.carparkservice.model.entity.Resident;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.ResidentService;

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
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;

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
    public List<VehicleDTO> getVehicles(long id) {
        return new ArrayList<VehicleDTO>(findById(id).getVehicles().stream().map(v -> vehicle2VehicleDTO.map(v)).collect(Collectors.toList()));
    }
}
