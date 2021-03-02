package by.training.airline.printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import by.training.airline.domain.Airline;
import by.training.airline.domain.CargoAirTransport;
import by.training.airline.domain.PassengerAirTransport;

public class FilePrinter implements PrinterVisitor {

    public static void printLine(String string) {
        try (FileWriter writer = new FileWriter("output_params.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(System.lineSeparator() + string);

        } catch (IOException e) {
            System.err.format("IOException: ", e);
        }
    }

    @Override
    public void visit(Airline airline) {
        String content = "Name: " + airline.getName() + "; Size: " + 
                airline.getTransportList().size() + " transport units; Total passenger capacity: " + 
                airline.getPassengerCapacity() +" ppl, Total lifting capacity: " + 
                airline.getLiftingCapacity() + " kg.";

        try (FileWriter writer = new FileWriter("output_params.txt", false);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(content + System.lineSeparator());

        } catch (IOException e) {
            System.err.format("IOException: ", e);
        }
    }

    @Override
    public void visit(CargoAirTransport cargoAirTransport) {
        String content = "Cargo Air Transport [ Name: " + cargoAirTransport.getName() + 
                "; Number Of Crew Members: " + cargoAirTransport.getNumberOfCrewMembers() + 
                " ppl; Fuel Consumption per one hour: " + cargoAirTransport.getFuelConsumption() + 
                " kg; Range Of Flight: " + cargoAirTransport.getRangeOfFlight() + 
                " km; Maximum Flight Altitude: " + cargoAirTransport.getMaxFlightAltitude() + 
                " km; Wing length: " + cargoAirTransport.getWingLength() + 
                " m; Cargo Capacity: " + cargoAirTransport.getCargoCapacity() + " kg.]";

        try (FileWriter writer = new FileWriter("output_params.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            
            bw.write(System.lineSeparator() + content);

        } catch (IOException e) {
            System.err.format("IOException: ", e);
        }
    }

    @Override
    public void visit(PassengerAirTransport passengerAirTransport) {
        String content = "Passenger Air Transport [ Name: " + passengerAirTransport.getName() + 
                "; Number Of Crew Members: " + passengerAirTransport.getNumberOfCrewMembers() + 
                " ppl; Fuel Consumption per one hour: " + passengerAirTransport.getFuelConsumption() + 
                " kg; Range Of Flight: " + passengerAirTransport.getRangeOfFlight() + 
                " km; Maximum Flight Altitude: " + passengerAirTransport.getMaxFlightAltitude() + 
                " km; Wing length: " + passengerAirTransport.getWingLength() + 
                " m; Passenger Capacity: " + passengerAirTransport.getPassengerCapacity() + " ppl.]";

        try (FileWriter writer = new FileWriter("output_params.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(System.lineSeparator() + content);

        } catch (IOException e) {
            System.err.format("IOException: ", e);
        }
    }
}
