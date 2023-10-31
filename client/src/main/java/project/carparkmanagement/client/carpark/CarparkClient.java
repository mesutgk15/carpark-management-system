package project.carparkmanagement.client.carpark;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("carpark-service")
public interface CarparkClient {

    @PutMapping("/carparks/vehicles/in")
    VehicleEntryResponse enterVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest);

    @PutMapping("/carparks/vehicles/out")
    VehicleEntryResponse exitVehicle(@RequestBody VehicleEntryRequest vehicleEntryRequest);
}
