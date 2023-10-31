package project.carparkmanagement.gateservice.service.implementation;

import project.carparkmanagement.client.carpark.CarparkClient;
import project.carparkmanagement.client.carpark.VehicleEntryRequest;
import project.carparkmanagement.client.carpark.VehicleEntryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.carparkmanagement.gateservice.model.GateLog;
import project.carparkmanagement.gateservice.repository.GateLogRepository;
import project.carparkmanagement.gateservice.service.GateService;

@Service
@RequiredArgsConstructor
public class GateServiceImpl implements GateService {


    private final CarparkClient carparkClient;
    private final GateLogRepository gateLogRepository;

    @Override
    public VehicleEntryResponse enterVehicle(VehicleEntryRequest vehicleEntryRequest) {

        System.out.printf("Enter initiated for (%s)...\nCalling CarparkService...\n", vehicleEntryRequest.getPlateNumber());

        //Calls carpark-service
        VehicleEntryResponse vehicleEntryResponse = carparkClient.enterVehicle(vehicleEntryRequest);

        GateLog gateLog = GateLog.builder()
                .Status(vehicleEntryResponse.isStatus())
                .direction(GateLog.Direction.ENTER.toString())
                .localDateTime(vehicleEntryResponse.getTime())
                .plateNumber(vehicleEntryResponse.getVehiclePlate())
                .build();

        gateLog.setReasonCode(vehicleEntryResponse.getReasonCode() != null ? vehicleEntryResponse.getReasonCode().toString() : null);

        if (vehicleEntryResponse.isStatus())
            System.out.println("Gate Opening...");
          else
            System.out.printf("Entry Failed For: %s (Reason Code: %s)\n", vehicleEntryRequest.getPlateNumber(), vehicleEntryResponse.getReasonCode());


        gateLogRepository.save(gateLog);

        return vehicleEntryResponse;

    }

    @Override
    public VehicleEntryResponse exitVehicle(VehicleEntryRequest vehicleEntryRequest) {


        System.out.printf("Exit initiated for (%s)\n Calling Carpark service...\n", vehicleEntryRequest.getPlateNumber());

        //Calls carpark-service
        VehicleEntryResponse vehicleEntryResponse = carparkClient.exitVehicle(vehicleEntryRequest);

        GateLog gateLog = GateLog.builder()
                .Status(vehicleEntryResponse.isStatus())
                .direction(GateLog.Direction.EXIT.toString())
                .localDateTime(vehicleEntryResponse.getTime())
                .plateNumber(vehicleEntryResponse.getVehiclePlate())
                .build();

        gateLog.setReasonCode(vehicleEntryResponse.getReasonCode() != null ? vehicleEntryResponse.getReasonCode().toString() : null);

        if (vehicleEntryResponse.isStatus())
            System.out.println("Gate Opening...");
        else
            System.out.printf("Exit Failed For: %s (Reason Code: %s)\n", vehicleEntryRequest.getPlateNumber(), vehicleEntryResponse.getReasonCode());


        gateLogRepository.save(gateLog);

        return vehicleEntryResponse;
    }
}
