package project.carparkmanagement.carparkservice.controller;

import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.carparkservice.mapper.Carpark2CarparkDTO;
import project.carparkmanagement.carparkservice.model.dto.CarparkDTO;
import project.carparkmanagement.carparkservice.service.CarparkService;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;

@RequestMapping("/carparks")
@RequiredArgsConstructor
@RestController
public class CarparkController {

    private final CarparkService carparkService;
    private final Carpark2CarparkDTO carpark2CarparkDTO;


    @PostMapping
    public CarparkDTO createCarpark(@RequestBody CarparkDTO carparkDTO) {
        return carparkService.save(carparkDTO);
    }

    @GetMapping("/{id}")
    public CarparkDTO findById(@PathVariable long id) {
        return carpark2CarparkDTO.map(carparkService.findById(id));
    }

    @PutMapping
    public CarparkDTO updateCarpark(@RequestBody CarparkDTO carparkDTO) {
        return carparkService.save(carparkDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCarpark(@PathVariable long id) {
        carparkService.delete(id);
    }

    @PutMapping("/vehicles/in")
    public VehicleEntryResponse enterVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest) {
        return carparkService.enterVehicle(vehicleEntryRequest);
    }

    @PutMapping("/vehicles/out")
    public VehicleEntryResponse exitVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest) {
        return carparkService.exitVehicle(vehicleEntryRequest);
    }

}
