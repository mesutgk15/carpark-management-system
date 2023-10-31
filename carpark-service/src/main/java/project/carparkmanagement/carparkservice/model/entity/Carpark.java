package project.carparkmanagement.carparkservice.model.entity;

import lombok.*;

import javax.persistence.*;

import java.util.*;



@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Carpark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String carparkID;
    private String name;
    private int capacity;



    @OneToMany
    private Set<Vehicle> vehiclesInside = new HashSet<>();
    @OneToMany
    private Set<Vehicle> vehiclesInsideConsumesSpace = new HashSet<>();

    public Carpark() {
    }

    public Carpark(String carparkID, String name, int capacity) {
        this.carparkID = carparkID;
        this.name = name;
        this.capacity = capacity;
    }


    //Overloaded, returns the vehicles inside (consumes spot)
    public List<Vehicle> getVehiclesInsideConsumesSpace(Resident resident) {
        List<Vehicle> vehicles = new ArrayList<>(getVehiclesInsideConsumesSpace());
        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext()) {
            if (it.next().getOwner() != resident) {
                it.remove();
            }
        }
        return vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carpark carpark = (Carpark) o;
        return Objects.equals(carparkID, carpark.carparkID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carparkID);
    }

    @Override
    public String toString() {
        return "Carpark{" +
                "carparkID='" + carparkID + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }


    public int getOccupancyRate() {
        int occupancy = vehiclesInsideConsumesSpace.size();
        System.out.printf("Occupancy Rate: %d / %d\n", occupancy, capacity );
        return occupancy;
    }


}
