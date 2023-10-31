package project.carparkmanagement.carparkservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.carparkservice.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.service.VehicleService;

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

    @PutMapping("/authorizations/{plateNumber}&{authorized}")
    public VehicleDTO setAuthorizationToGetIn(@PathVariable String plateNumber, @PathVariable boolean authorized) {
        return vehicleService.setAuthorizedToGetIn(plateNumber, authorized);
    }

    @PutMapping("/owners/{plate_number}&{residentId}")
    public VehicleDTO setOwner(@PathVariable String plate_number, @PathVariable long residentId) {
        return vehicleService.setOwner(plate_number, residentId);
    }
}
