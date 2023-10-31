package project.carparkmanagement.gateservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.carparkmanagement.client.carpark.enums.ReasonCodesGate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GateLog {

    public enum Direction {
        ENTER,
        EXIT
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String plateNumber;
    private String direction;
    private LocalDateTime localDateTime;
    private boolean Status;
    private String reasonCode;
}
