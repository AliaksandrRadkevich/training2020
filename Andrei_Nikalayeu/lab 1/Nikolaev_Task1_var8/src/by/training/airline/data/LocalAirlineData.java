package by.training.airline.data;

import java.util.ArrayList;
import java.util.List;

import by.training.airline.domain.AirTransport;
import by.training.airline.domain.Airline;
import by.training.airline.domain.CargoAirTransport;
import by.training.airline.domain.PassengerAirTransport;

public class LocalAirlineData implements AirlineData{
    @Override
    public Airline read() {
        List<AirTransport> airlineTransport = new ArrayList<AirTransport>();
        airlineTransport.add(new PassengerAirTransport("An 2", 2, 140.0, 845.0, 4.2, 18.0, 12));
        airlineTransport.add(new CargoAirTransport("An 124", 6, 12700.0, 16000.0, 11.6, 73.0, 124000.0));
        airlineTransport.add(new PassengerAirTransport("An 24", 4, 800.0, 2761.0, 6.0, 29.0, 52));
        airlineTransport.add(new PassengerAirTransport("IL 86", 15, 9900.0, 4600.0, 10.5, 48.0, 250));
        airlineTransport.add(new CargoAirTransport("An 12", 5, 1950.0, 5700.0, 8.5, 38.0, 19500.0));

        return new Airline("Small Airline", airlineTransport);
    }
}
