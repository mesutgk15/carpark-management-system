package project.carparkmanagement.carparkservice.model.entity;

import javax.persistence.Entity;

@Entity
public class Motorbike extends Vehicle {

    public Motorbike() {
    }

    public Motorbike(String plateNumber, FuelType fuelType) {
        super(plateNumber, fuelType);
        super.setConsumesSpace(false);
    }
}
