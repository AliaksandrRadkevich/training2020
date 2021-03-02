package by.training.airline.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import by.training.airline.domain.AirTransport;
import by.training.airline.domain.Airline;
import by.training.airline.domain.CargoAirTransport;
import by.training.airline.domain.PassengerAirTransport;

public class FileAirlineData implements AirlineData {
    public static String paramTrim(String string) {
        return string.substring(string.indexOf("=")+1, string.length()).trim();
    }

    @Override
    public Airline read() {
        Airline airline = new Airline();
        List<AirTransport> airlineTransport = new ArrayList<AirTransport>();
        try {
            File file = new File("input_params.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<String> passengerTransportParams = new ArrayList<String>();
            List<String> cargoTransportParams = new ArrayList<String>();

            String line = reader.readLine();

            while (line != null) {
                if (!line.isEmpty()) {
                    //search for airline name
                    if (line.contains("airlineName")) {
                        airline.setName(FileAirlineData.paramTrim(line));
                    }
                    //search for a string containing passenger transport parameters
                    if (line.contains("passenger")) {
                            passengerTransportParams.add(line.trim());                        
                    }
                    //search for a string containing cargo transport parameters
                    if (line.contains("cargo")) {
                        cargoTransportParams.add(line.trim()); 
                    }
                }
                line = reader.readLine();
            }

            //splitting the string into parameters and creating a specific object
            for (String item : passengerTransportParams) {
                PassengerAirTransport airTransport = new PassengerAirTransport();
                for (String string : item.split(";")) {
                    if (string.contains("name")) {
                        airTransport.setName(FileAirlineData.paramTrim(string));
                    }
                    if (string.contains("numberOfCrewMembers")) {
                        airTransport.setNumberOfCrewMembers(Integer.parseInt(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("fuelConsumptionPerOneHour")) {
                        airTransport.setFuelConsumption(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("rangeOfFlight")) {
                        airTransport.setRangeOfFlight(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("maximumFlightAltitude")) {
                        airTransport.setMaxFlightAltitude(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("wingLength")) {
                        airTransport.setWingLength(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("passengerCapacity")) {
                        airTransport.setPassengerCapacity(Integer.parseInt(FileAirlineData.paramTrim(string)));
                    }
                }

                airlineTransport.add(airTransport);
            }

            //splitting the string into parameters and creating a specific object
            for (String item : cargoTransportParams) {
                CargoAirTransport airTransport = new CargoAirTransport();
                for (String string : item.split(";")) {
                    if (string.contains("name")) {
                        airTransport.setName(FileAirlineData.paramTrim(string));
                    }
                    if (string.contains("numberOfCrewMembers")) {
                        airTransport.setNumberOfCrewMembers(Integer.parseInt(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("fuelConsumptionPerOneHour")) {
                        airTransport.setFuelConsumption(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("rangeOfFlight")) {
                        airTransport.setRangeOfFlight(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("maximumFlightAltitude")) {
                        airTransport.setMaxFlightAltitude(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("wingLength")) {
                        airTransport.setWingLength(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                    if (string.contains("cargoCapacity")) {
                        airTransport.setCargoCapacity(Double.parseDouble(FileAirlineData.paramTrim(string)));
                    }
                }

                airlineTransport.add(airTransport);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + "\nObject Airline return null");
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        airline.setTransportList(airlineTransport);
        return airline;
    }
}
