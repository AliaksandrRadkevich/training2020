package by.training.airline.printer;

import by.training.airline.domain.Airline;
import by.training.airline.domain.CargoAirTransport;
import by.training.airline.domain.PassengerAirTransport;

public class ConsolePrinter implements PrinterVisitor {

    @Override
    public void visit(Airline airline) {
        System.out.println("Name: " + airline.getName() + 
                ",\nSize: " + airline.getTransportList().size() + 
                " transport units,\nTotal passenger capacity: " + airline.getPassengerCapacity() +
                " ppl,\nTotal lifting capacity: " + airline.getLiftingCapacity() + " kg.");
    }

    @Override
    public void visit(CargoAirTransport cargoAirTransport) {
        System.out.println("\nCargo Air Transport [\n Name: " + cargoAirTransport.getName() + 
                ",\n Number Of Crew Members: " + cargoAirTransport.getNumberOfCrewMembers() +
                " ppl,\n Fuel Consumption per one hour: " + cargoAirTransport.getFuelConsumption() +
                " kg,\n Range Of Flight: " + cargoAirTransport.getRangeOfFlight() + 
                " km, \n Maximum Flight Altitude: " + cargoAirTransport.getMaxFlightAltitude() + 
                " km, \n Wing length: " + cargoAirTransport.getWingLength() +
                " m,\n Cargo Capacity: " + cargoAirTransport.getCargoCapacity() + " kg\n]"); 
    }

    @Override
    public void visit(PassengerAirTransport passengerAirTransport) {
        System.out.println("\nPassenger Air Transport [\n Name: " + passengerAirTransport.getName() + 
                ",\n Number Of Crew Members: " + passengerAirTransport.getNumberOfCrewMembers() +
                " ppl,\n Fuel Consumption per one hour: " + passengerAirTransport.getFuelConsumption() +
                " kg,\n Range Of Flight: " + passengerAirTransport.getRangeOfFlight() + 
                " km, \n Maximum Flight Altitude: " + passengerAirTransport.getMaxFlightAltitude() + 
                " km, \n Wing length: " + passengerAirTransport.getWingLength() +
                " m,\n Passenger Capacity: " + passengerAirTransport.getPassengerCapacity() + " ppl\n]"); 
    }
}
