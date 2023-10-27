package project.carparkmanagement.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.carparkmanagement.admin.mapper.Carpark2CarparkDTO;
import project.carparkmanagement.admin.mapper.CarparkDTO2Carpark;
import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.service.CarparkService;
import project.carparkmanagement.admin.service.impl.CarparkServiceImpl;

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

    @PutMapping("/vehicles/in/{carparkid}&{vehicleid}")
    public void enterVehicle(@PathVariable long carparkid, @PathVariable long vehicleid) {
        carparkService.enterVehicle(carparkid, vehicleid);
    }
}
