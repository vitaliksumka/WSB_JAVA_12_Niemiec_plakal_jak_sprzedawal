package com.company.devices;

public class DieselCar extends Car {
    public DieselCar(String producer, String model) {
        super(producer, model);
    }

    @Override
    public void refuel() {
        System.out.println("Zatankowałeś do pełna!");
    }

    public String toString() {
        return "Diesel car: " + this.producer + " " + this.model + " " + this.getPlates();
    }
}
