package by.training.airline.logic;

import java.util.Comparator;

import by.training.airline.domain.AirTransport;

public class RangeOfFlightComparator implements Comparator<AirTransport> {
    @Override
    public int compare(AirTransport airTransport1, AirTransport airTransport2) {
        return Double.compare(airTransport1.getRangeOfFlight(), airTransport2.getRangeOfFlight());
    }
}
