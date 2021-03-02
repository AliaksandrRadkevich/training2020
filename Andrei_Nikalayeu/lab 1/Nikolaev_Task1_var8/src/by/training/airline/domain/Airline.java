package by.training.airline.domain;

import java.util.List;

import by.training.airline.printer.PrinterVisitor;
import by.training.airline.printer.Visitor;

public class Airline implements Visitor{
    private String name;
    private List<AirTransport> transportList;

    public Airline() {}

    public Airline(String name, List<AirTransport> transportList) {
        this.name = name;
        this.transportList = transportList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AirTransport> getTransportList() {
        return transportList;
    }

    public void setTransportList(List<AirTransport> transportList) {
        this.transportList = transportList;
    }

    public double getLiftingCapacity() {
        double totalLiftingCapacity = 0;
        for (AirTransport airTransport : transportList) {
            if(airTransport instanceof CargoAirTransport) {
                totalLiftingCapacity += airTransport.getCapacity();
            }
        }
        return totalLiftingCapacity;
    }

    public int getPassengerCapacity() {
        int totalPassengerCapacity = 0;
        for (AirTransport airTransport : transportList) {
            if(airTransport instanceof PassengerAirTransport) {
                totalPassengerCapacity += airTransport.getCapacity();
            }
        }
        return totalPassengerCapacity;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + 
                ",\nSize: " + getTransportList().size() + 
                " transport units,\nTotal passenger capacity: " + getPassengerCapacity() +
                " ppl,\nTotal lifting capacity: " + getLiftingCapacity() + " kg.";
    }

    @Override
    public void accept(PrinterVisitor printerVisitor) {
        printerVisitor.visit(this);
    }
}
