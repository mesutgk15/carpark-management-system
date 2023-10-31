package project.carparkmanagement.carparkservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.carparkservice.mapper.Resident2ResidentDTO;
import project.carparkmanagement.carparkservice.model.dto.ResidentDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.ResidentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;
    private final Resident2ResidentDTO resident2ResidentDTO;

    @GetMapping
    public List<ResidentDTO> findAll() {
        return residentService.findAll();
    }

    @GetMapping("/{id}")
    public ResidentDTO findById(@PathVariable long id) {
        return resident2ResidentDTO.map(residentService.findById(id));
    }

    @PostMapping
    public ResidentDTO save(@RequestBody ResidentDTO residentDTO) {
        return residentService.save(residentDTO);
    }

    @PutMapping
    public ResidentDTO update(@RequestBody ResidentDTO residentDTO) {
        return residentService.save(residentDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        residentService.delete(id);
    }

    @GetMapping("/vehicles/{id}")
    public List<VehicleDTO> getVehicles(@PathVariable long id) {
        return residentService.getVehicles(id);
    }

}
