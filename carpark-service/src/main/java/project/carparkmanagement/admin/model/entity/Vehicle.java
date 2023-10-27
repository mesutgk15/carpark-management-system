package project.carparkmanagement.admin.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    public enum FuelType {GAS, ELECTRIC};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String plateNumber;
    private FuelType fuelType;
    private boolean authorizedToGetIn = true;
    private boolean consumesSpace;

    @ManyToOne
    @JsonManagedReference
    private Resident owner;

    public Vehicle() {

    }

    public Vehicle(String plateNumber, FuelType fuelType) {
        this.plateNumber = plateNumber;
        this.fuelType = fuelType;
        this.authorizedToGetIn = false;
    }

    //There should not be a vehicle without owner
//    public void deleteOwner() {
//        this.owner = null;
//        System.out.printf("-Owner deleted for Vehicle (%s)\n", plateNumber);
//        if (owner.removeVehicle(this)) {
//            System.out.printf("-Vehicle (%s) removed from Resident's Vehicle list\n", plateNumber);
//        }
//    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(plateNumber, vehicle.plateNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plateNumber);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {" +
                "plateNumber='" + plateNumber + '\'' +
                ", fuelType=" + fuelType +
                ", authorizationToGetIn=" + authorizedToGetIn +
                ", consumesSpot=" + consumesSpace +
                ", owner=" + (owner == null ? "Undefined Owner" : owner.getName())  +
                '}';
    }
}
