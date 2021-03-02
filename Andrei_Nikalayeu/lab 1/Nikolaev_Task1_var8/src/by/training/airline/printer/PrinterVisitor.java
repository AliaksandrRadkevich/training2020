package by.training.airline.printer;

import by.training.airline.domain.Airline;
import by.training.airline.domain.CargoAirTransport;
import by.training.airline.domain.PassengerAirTransport;

public interface PrinterVisitor {
    void visit(Airline airline);
    void visit(CargoAirTransport cargoAirTransport);
    void visit(PassengerAirTransport passengerAirTransport);
}
