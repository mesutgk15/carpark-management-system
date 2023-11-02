package project.carparkmanagement.carparkservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.carparkmanagement.carparkservice.mapper.Resident2ResidentDTO;
import project.carparkmanagement.carparkservice.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.carparkservice.service.impl.ResidentServiceImpl;
import project.carparkmanagement.carparkservice.model.dto.CarparkDTO;
import project.carparkmanagement.carparkservice.model.dto.VehicleDTO;
import project.carparkmanagement.carparkservice.model.entity.Car;
import project.carparkmanagement.carparkservice.model.entity.Motorbike;
import project.carparkmanagement.carparkservice.model.entity.Resident;
import project.carparkmanagement.carparkservice.model.entity.Vehicle;
import project.carparkmanagement.carparkservice.service.impl.CarparkServiceImpl;
import project.carparkmanagement.carparkservice.service.impl.VehicleServiceImpl;
import project.carparkmanagement.client.carpark.CarparkClient;
import project.carparkmanagement.client.carpark.VehicleEntryRequest;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitializerTestClient implements ApplicationRunner {

    private final CarparkServiceImpl carparkServiceImpl;
    private final ResidentServiceImpl residentService;
    private final VehicleServiceImpl vehicleServiceImpl;
    private final Vehicle2VehicleDTO vehicle2VehicleDTO;
    private final Resident2ResidentDTO resident2ResidentDTO;



    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        if (vehicleServiceImpl.findAll().size() == 0) {

            Vehicle vehicle1 = new Car("34ABCD123", Vehicle.FuelType.GAS);
            Vehicle vehicle2 = new Car("06ABCD123", Vehicle.FuelType.ELECTRIC);
            Vehicle vehicle3 = new Car("34ABCD321", Vehicle.FuelType.GAS);
            Vehicle vehicle4 = new Car("35ABCD213", Vehicle.FuelType.ELECTRIC);
            Vehicle vehicle5 = new Car("34ABCD312", Vehicle.FuelType.GAS);
            Vehicle vehicle6 = new Motorbike("34FGHJ123", Vehicle.FuelType.ELECTRIC);

            Resident resident1 = Resident.builder()
                    .name("Mehmet Demir")
                    .contactNumber("+901234567889")
                    .apartmentNumber("A201")
                    .carparkAllowance(1)
                    .build();
            Resident resident2 = Resident.builder()
                    .name("Ali Yıldız")
                    .contactNumber("+9008766554221")
                    .apartmentNumber("B402")
                    .carparkAllowance(2)
                    .build();

            CarparkDTO carparkDTO1 = CarparkDTO.builder()
                    .carparkID("A1")
                    .name("Building-S1")
                    .capacity(5)
                    .build();

            residentService.save(resident2ResidentDTO.map(resident1));
            residentService.save(resident2ResidentDTO.map(resident2));


            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle1));
            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle2));
            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle3));
            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle4));
            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle5));
            vehicleServiceImpl.save(vehicle2VehicleDTO.map(vehicle6));


            carparkServiceImpl.save(carparkDTO1);

            System.out.println("Resident 1 : " + resident1);
            System.out.println("Resident 2 : " + resident2);
            System.out.println("Vehicle 1 : " + vehicle1);
            System.out.println("Vehicle 2 : " + vehicle2);
            System.out.println("Vehicle 3 : " + vehicle3);
            System.out.println("Vehicle 4 : " + vehicle4);
            System.out.println("Vehicle 5 : " + vehicle5);
            System.out.println("Vehicle 6 : " + vehicle6);

            System.out.println("Carpark 1 Vehicles : " + carparkDTO1);
            System.out.println("-".repeat(40));

            System.out.println("MANIPULATION TESTS ");
            vehicleServiceImpl.setOwner("34ABCD123", 1);
            System.out.println("\tAdding Vehicle to Resident for second time");
            vehicleServiceImpl.setOwner("34ABCD123", 1);
            System.out.println("\tAdding Vehicle to another Resident");
            vehicleServiceImpl.setOwner("34ABCD123", 2);
            System.out.println("\tRemoving Vehicle not belongs to Resident");
            vehicle2.setOwner(resident2);
            vehicle1.setOwner(resident1);
            System.out.println("\tRemoving Vehicle");
            vehicle1.setOwner(null);
            vehicleServiceImpl.setOwner("34ABCD123", 1);


            System.out.println("-".repeat(40));

            System.out.println("Resident 1 Vehicles : " + resident1.getVehicles());
            System.out.println("Resident 2 Vehicles : " + resident2.getVehicles());

            System.out.println("-".repeat(40));
            System.out.println("VEHICLES INSIDE");
            System.out.println(carparkDTO1.getVehiclesInside());
            System.out.println(carparkDTO1.getVehiclesInsideConsumesSpace());

            System.out.println("-".repeat(40));


        }
    }
}
