package project.carparkmanagement.carparkservice.model.entity;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle{

    public Car() {
    }

    public Car(String plateNumber, FuelType fuelType) {
        super(plateNumber, fuelType);
        super.setConsumesSpace(true);
    }

}
