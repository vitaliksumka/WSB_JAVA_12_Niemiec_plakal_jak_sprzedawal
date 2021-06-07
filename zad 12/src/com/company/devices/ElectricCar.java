package com.company.devices;

public class ElectricCar extends Car {
    public ElectricCar(String producer, String model) {
        super(producer, model);
    }

    @Override
    public void refuel() {
        System.out.println("Naładowałeś samochód!");
    }

    public String toString() {
        return "Electric car: " + this.producer + " " + this.model + " " + this.getPlates();
    }
}
