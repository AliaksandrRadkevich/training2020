package by.training.airline.domain;

import by.training.airline.printer.PrinterVisitor;

public class CargoAirTransport extends AirTransport{
    private static final double AVERAGE_HUMAN_WEIGHT = 80.0;
    private double cargoCapacity;

    public CargoAirTransport() {}

    public CargoAirTransport(String name, int numberOfCrewMembers, double fuelConsumption, double rangeOfFlight, double maxFlightAltitude, double wingLength, double cargoCapacity) {
        super(name, numberOfCrewMembers, fuelConsumption, rangeOfFlight, maxFlightAltitude, wingLength);
        this.cargoCapacity = cargoCapacity;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double getCapacity() {
        return AVERAGE_HUMAN_WEIGHT * getNumberOfCrewMembers() + getCargoCapacity();
    }

    @Override
    public void accept(PrinterVisitor printerVisitor) {
        printerVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "\nCargo Air Transport [\n Name: " + getName() + 
                ",\n Number Of Crew Members: " + getNumberOfCrewMembers() +
                " ppl,\n Fuel Consumption per one hour: " + getFuelConsumption() +
                " kg,\n Range Of Flight: " + getRangeOfFlight() + 
                " km, \n Maximum Flight Altitude: " + getMaxFlightAltitude() + 
                " km, \n Wing length: " + getWingLength() +
                " m,\n Cargo Capacity: " + getCargoCapacity() + " kg\n]"; 
    }
}
