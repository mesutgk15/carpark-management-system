package project.carparkmanagement.gateservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;
import project.carparkmanagement.gateservice.service.GateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("gate")
public class GateController {

    private final GateService gateService;
    @PutMapping("/in")
    public VehicleEntryResponse enterVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest) {
        return gateService.enterVehicle(vehicleEntryRequest);
    }

    @PutMapping("/out")
    public VehicleEntryResponse exitVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest) {
        return gateService.exitVehicle(vehicleEntryRequest);
    }
}
