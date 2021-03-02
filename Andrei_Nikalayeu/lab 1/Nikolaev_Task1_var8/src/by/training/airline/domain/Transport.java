package by.training.airline.domain;

import by.training.airline.printer.Visitor;

public abstract class Transport implements Visitor{
    private String name;
    private int numberOfCrewMembers;
    private double fuelConsumption;

    public Transport() {}

    public Transport(String name, int numberOfCrewMembers, double fuelConsumption) {
        this.name = name;
        this. numberOfCrewMembers = numberOfCrewMembers;
        this.fuelConsumption = fuelConsumption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCrewMembers() {
        return numberOfCrewMembers;
    }

    public void setNumberOfCrewMembers(int numberOfCrewMembers) {
        this.numberOfCrewMembers = numberOfCrewMembers;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public abstract double getCapacity();
}
