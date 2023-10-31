package project.carparkmanagement.carparkservice.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Entity
@Component
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String contactNumber;
    private String apartmentNumber;
    private int carparkAllowance;

    @OneToMany(mappedBy = "owner")
    @JsonBackReference
    private Set<Vehicle> vehicles = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(name, resident.name) && Objects.equals(apartmentNumber, resident.apartmentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, apartmentNumber);
    }


    @Override
    public String toString() {
        return "Resident{" +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", carparkAllowance=" + carparkAllowance +
                '}';
    }


}
