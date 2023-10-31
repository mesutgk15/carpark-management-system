package project.carparkmanagement.client.carpark;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import project.carparkmanagement.client.carpark.enums.ReasonCodesGate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntryResponse {
    private String vehiclePlate;
    private String carparkID;
    private boolean status;
    private LocalDateTime time;
    private ReasonCodesGate reasonCode;
}
