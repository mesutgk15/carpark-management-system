package project.carparkmanagement.admin.client;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import project.carparkmanagement.admin.mapper.Resident2ResidentDTO;
import project.carparkmanagement.admin.mapper.Vehicle2VehicleDTO;
import project.carparkmanagement.admin.model.dto.CarparkDTO;
import project.carparkmanagement.admin.model.dto.VehicleDTO;
import project.carparkmanagement.admin.model.entity.Car;
import project.carparkmanagement.admin.model.entity.Motorbike;
import project.carparkmanagement.admin.model.entity.Resident;
import project.carparkmanagement.admin.model.entity.Vehicle;
import project.carparkmanagement.admin.service.impl.CarparkServiceImpl;
import project.carparkmanagement.admin.service.impl.ResidentServiceImpl;
import project.carparkmanagement.admin.service.impl.VehicleServiceImpl;

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
            vehicleServiceImpl.setOwner(1, 1);
            System.out.println("\tAdding Vehicle to Resident for second time");
            vehicleServiceImpl.setOwner(1, 1);
            System.out.println("\tAdding Vehicle to another Resident");
            vehicleServiceImpl.setOwner(1, 2);
            System.out.println("\tRemoving Vehicle not belongs to Resident");
            residentService.removeVehicle(resident2, vehicle2);
            residentService.removeVehicle(resident1, vehicle1);
            System.out.println("\tRemoving Vehicle");
            residentService.removeVehicle(resident2, vehicle1);
            vehicleServiceImpl.setOwner(1, 1);


            System.out.println("-".repeat(40));
            System.out.println("CARPARK TESTS");
            System.out.println("\tExit Vehicle not inside");
            carparkServiceImpl.exitVehicle(1, 1);
            System.out.println("\tEnter unauthorized vehicle");
            carparkServiceImpl.enterVehicle(1, 1);
            System.out.println("\tEnter authorized vehicle");
            vehicle1.setAuthorizedToGetIn(true);
            carparkServiceImpl.enterVehicle(1, 1);
            System.out.println("\tEnter vehicle with exceeded allowance");
            vehicleServiceImpl.setOwner(2, 1);
            vehicle2.setAuthorizedToGetIn(true);
            carparkServiceImpl.enterVehicle(1, 2);
            System.out.println("\tEnter vehicle with exceeded allowance but vehicle does not consume spot");
            vehicle6.setOwner(resident1);
            vehicle6.setAuthorizedToGetIn(true);
            carparkServiceImpl.enterVehicle(1, 6);

            System.out.println("-".repeat(40));

            System.out.println("Resident 1 Vehicles : " + resident1.getVehicles());
            System.out.println("Resident 2 Vehicles : " + resident2.getVehicles());

            System.out.println("-".repeat(40));
            System.out.println("VEHICLES INSIDE");
            System.out.println(carparkDTO1.getVehiclesInside());
            System.out.println(carparkDTO1.getVehiclesInsideConsumesSpace());

            System.out.println("-".repeat(40));
            System.out.println("EXIT ALL VEHICLES");


            List<VehicleDTO> vehicles = new ArrayList<>(carparkDTO1.getVehiclesInside());
//        for (Vehicle v : vehicles) {
//            carpark1.getOccupancyRate();
//            carparkService.exitVehicle(carpark1, v);
//            carpark1.getOccupancyRate();
//
//            for (int i = 0; i < 20; i++) {
//                System.out.printf(i == 19 ? ".\n" : ".");
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
        }
    }
}
