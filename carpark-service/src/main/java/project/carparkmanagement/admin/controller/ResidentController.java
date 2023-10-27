package project.carparkmanagement.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.admin.mapper.Resident2ResidentDTO;
import project.carparkmanagement.admin.model.dto.ResidentDTO;
import project.carparkmanagement.admin.model.entity.Resident;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.service.ResidentService;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Vehicle> getVehicles(@PathVariable long id) {
        return residentService.getVehicles(id);
    }

}
