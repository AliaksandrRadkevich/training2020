package by.training.airline.main;

import by.training.airline.domain.AirTransport;
import by.training.airline.domain.Airline;
import by.training.airline.logic.Filter;
import by.training.airline.logic.FuelConsumptionFilter;
import by.training.airline.logic.RangeOfFlightComparator;
import by.training.airline.printer.ConsolePrinter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import by.training.airline.data.LocalAirlineData;

public class Main {

    public static void main(String[] args) {
        LocalAirlineData localAirlineData = new LocalAirlineData();
//        FileAirlineData fileAirlineData = new FileAirlineData();
        Airline airline = localAirlineData.read();

        int filterCount = 0;

        airline.accept(new ConsolePrinter());

        System.out.println("\n===Transport list===");

        for (AirTransport airTransport : airline.getTransportList()) {
            airTransport.accept(new ConsolePrinter());
        }

        System.out.println("\n===Fuel Consumption Filter===");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the minimum fuel consumption: ");
        Double minFuelConsumption = sc.nextDouble();
        System.out.print("Enter the maximum fuel consumption: ");
        Double maxFuelConsumption = sc.nextDouble();

        sc.close();

        System.out.println("\nTransport, where fuel consumption is from "+ minFuelConsumption + " to " + maxFuelConsumption);
        
        Filter fuelConsumptionFilter = new FuelConsumptionFilter(minFuelConsumption, maxFuelConsumption);
        for (AirTransport airTransport : airline.getTransportList()) {
            if (fuelConsumptionFilter.isAppropriate(airTransport)) {
                airTransport.accept(new ConsolePrinter());
                filterCount++;
            }
        }

        System.out.println("\n" + filterCount + " units");

        Comparator<AirTransport> rangeOfFlightComparator = new RangeOfFlightComparator();

        Collections.sort(airline.getTransportList(), rangeOfFlightComparator);

        System.out.println("\n===Sorting transport by flight range===");

        for (AirTransport airTransport : airline.getTransportList()) {
            airTransport.accept(new ConsolePrinter());
        }
    }
}
