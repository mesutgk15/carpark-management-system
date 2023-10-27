package project.carparkmanagement.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.admin.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.service.VehicleService;

import java.util.List;


@RequestMapping("vehicles")
@RequiredArgsConstructor
@RestController
public class VehicleController {

    private final VehicleService vehicleService;
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;

    @GetMapping
    public List<VehicleDTO> findAll() {
        return vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public VehicleDTO findById(@PathVariable long id) {
        return vehicle2VehicleDTO.map(vehicleService.findById(id));
    }

    @PostMapping
    public VehicleDTO save(@RequestBody VehicleDTO vehicleDTO) {
         return vehicleService.save(vehicleDTO);
    }

    @PutMapping
    public VehicleDTO update(@RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.save(vehicleDTO);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable long id) {
        vehicleService.delete(id);
    }

    @PutMapping("/authorizations/{vehicleId}&{authorized}")
    public VehicleDTO setAuthorizationToGetIn(@PathVariable long vehicleId, @PathVariable boolean authorized) {
        return vehicleService.setAuthorizedToGetIn(vehicleId, authorized);
    }

    @PutMapping("/owners/{vehicleId}&{residentId}")
    public VehicleDTO setOwner(@PathVariable long vehicleId, @PathVariable long residentId) {
        return vehicleService.setOwner(vehicleId, residentId);
    }
}
