package com.company;

import com.company.creatures.Animal;
import com.company.devices.Car;
import com.company.devices.CarComparator;
import com.company.devices.Device;
import com.company.devices.Phone;
import org.jetbrains.annotations.Nullable;
import java.sql.Timestamp;
import java.util.Arrays;

public class Human extends Animal {
    public String firstName;
    public String lastName;
    public Animal pet;
    public Double cash = 10000.0;
    protected Phone mobile;
    private Car[] garage;
    private Double salary = 3000.0;
    private Timestamp lastSalaryCheckTimestamp;
    private Double lastSalaryCheckValue;

    public Human() {
        super("Human");
    }

    public Human(String firstName, String lastName) {
        super("Human");
        this.firstName = firstName;
        this.lastName = lastName;
        this.garage = new Car[1];
    }

    public Human(String firstName, String lastName, Integer numberOfParkingSpaces) {
        super("Human");
        this.firstName = firstName;
        this.lastName = lastName;
        this.garage = new Car[numberOfParkingSpaces];
    }

    public Double getSalary() {
        lastSalaryCheckTimestamp = new Timestamp(System.currentTimeMillis());
        lastSalaryCheckValue = salary;
        return salary;
    }

    public void setSalary(Double salary) {
        if (salary < 0) {
            System.out.println("Wartość pensji nie może być mniejsza od zera!");
        } else {
            System.out.println("Nowa wartość pensji przekazana do systemu ksiągowego!");
            System.out.println("Aneks do umowy do odebrania od pani Hani z kadr.");
            System.out.println("US i ZUS już wiedzą o zmianaie wysokosci wypłaty ;)");
            this.salary = salary;
        }
    }

    public void getLastSalaryCheckInfo() {
        if (lastSalaryCheckValue == null) {
            System.out.println("Ostatnie sprawdzanie pensji: brak");
        } else {
            System.out.println("Ostatnie sprawdzanie pensji: " + lastSalaryCheckTimestamp + " Pensja: " + lastSalaryCheckValue);
        }
    }

    public Car getCar(Integer numberParkingSpace) {
        if (numberParkingSpace >= garage.length) {
            System.out.println("Nie masz takiego miejsca parkingowego w garażu!");
            return null;
        } else {
            return garage[numberParkingSpace];
        }
    }

    public void setCar(Integer numberParkingSpace, Car car) {
        if (numberParkingSpace >= garage.length) {
            System.out.println("Nie masz takiego miejsca parkingowego w garażu!");
        } else if (garage[numberParkingSpace] != null) {
            System.out.println("To miejsce parkingowe jest już zajęte!");
        } else {
            garage[numberParkingSpace] = car;
        }
    }

    public void removeCar(Car car) throws Exception {
        boolean success = false;
        if (hasDevice(car)) {
            for (Car myCar : garage) {
                if (myCar == car) {
                    myCar = null;
                }
            }
            success = true;
        }
        if (!success) {
            throw new Exception("Nie możesz usunąć samochodu, którego nie posiadasz!");
        }
    }

    public Double getTotalValueOfCars() {
        Double sum = 0.0;
        for (Car myCar : garage) {
            if (myCar != null) {
                sum += myCar.value;
            }
        }
        return sum;
    }

    @Nullable
    public Integer getNumberParkingFreeSpace() {
        for (int i = 0; i < garage.length; i++) {
            if (garage[i] == null) {
                return i;
            }
        }
        return null;
    }

    public void sortMyCarsByYear() {
        Arrays.sort(garage, new CarComparator());
    }

    public boolean hasDevice(Device device) throws Exception {
        if (device instanceof Car) {
            for (Car myCar : garage) {
                return myCar == device;
            }
            throw new Exception("Nie posiadasz takiego samochodu!");
        } else if (device instanceof Phone) {
            return this.mobile == device;
        }
        return false;
    }

    public boolean hasAnimal(Animal animal) {
        return this.pet == animal;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public boolean couldBuy(Device device, Double price) throws Exception {
        if (device instanceof Car) {
            boolean hasFreeSpace = false;
            for (Car space : garage) {
                if (space == null) {
                    hasFreeSpace = true;
                    break;
                }
            }

            if (!hasFreeSpace) {
                throw new Exception("Nie masz wolnego miejsca w garażu!");
            }
        }

        if (price <= this.cash) {
            return true;
        } else {
            throw new Exception("Nie masz wystarczajacej ilości gotówki!");
        }
    }

    public boolean couldBuy(Animal pet, Double price) {
        return price <= this.cash;
    }

    public void setMobile(Phone mobile) {
        this.mobile = mobile;
    }

    @Override
    public void sell(Human seller, Human buyer, Double price) throws Exception {
        throw new Exception("Nie możesz sprzedawać ludzi!");
    }
}
