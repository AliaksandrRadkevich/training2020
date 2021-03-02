package by.training.airline.domain;

import by.training.airline.printer.PrinterVisitor;

public class PassengerAirTransport extends AirTransport{
    private int passengerCapacity;

    public PassengerAirTransport() {}

    public PassengerAirTransport(String name, int numberOfCrewMembers, double fuelConsumption, double rangeOfFlight, double maxFlightAltitude, double wingLength, int passengerCapacity) {
        super(name, numberOfCrewMembers, fuelConsumption, rangeOfFlight, maxFlightAltitude, wingLength);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double getCapacity() {
        return getNumberOfCrewMembers() + getPassengerCapacity();
    }

    @Override
    public void accept(PrinterVisitor printerVisitor) {
        printerVisitor.visit(this);
    }

    @Override
    public String toString() {
        return "\nPassenger Air Transport [\n Name: " + getName() + 
                ",\n Number Of Crew Members: " + getNumberOfCrewMembers() +
                " ppl,\n Fuel Consumption per one hour: " + getFuelConsumption() +
                " kg,\n Range Of Flight: " + getRangeOfFlight() + 
                " km, \n Maximum Flight Altitude: " + getMaxFlightAltitude() + 
                " km, \n Wing length: " + getWingLength() +
                " m,\n Passenger Capacity: " + getPassengerCapacity() + " ppl\n]";
    }
}
