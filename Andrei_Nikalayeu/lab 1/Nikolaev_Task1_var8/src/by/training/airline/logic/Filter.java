package by.training.airline.logic;

import by.training.airline.domain.AirTransport;

public interface Filter {
    boolean isAppropriate(AirTransport transport);
}
