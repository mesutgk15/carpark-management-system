package project.carparkmanagement.client.carpark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntryRequest {
    private String plateNumber;
    private String carparkID;
}
