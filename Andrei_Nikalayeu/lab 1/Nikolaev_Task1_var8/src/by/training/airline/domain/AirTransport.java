package by.training.airline.domain;

public abstract class AirTransport extends Transport{
    private double rangeOfFlight;
    private double maxFlightAltitude;
    private double wingLength;

    public AirTransport() {}

    public AirTransport(String name, int numberOfCrewMembers, double fuelConsumption, double rangeOfFlight, double maxFlightAltitude, double wingLength) {
        super(name, numberOfCrewMembers, fuelConsumption);
        this.rangeOfFlight = rangeOfFlight;
        this.maxFlightAltitude = maxFlightAltitude;
        this.wingLength = wingLength;
    }

    public double getRangeOfFlight() {
        return rangeOfFlight;
    }

    public void setRangeOfFlight(double rangeOfFlight) {
        this.rangeOfFlight = rangeOfFlight;
    }

    public double getMaxFlightAltitude() {
        return maxFlightAltitude;
    }

    public void setMaxFlightAltitude(double maxFlightAltitude) {
        this.maxFlightAltitude = maxFlightAltitude;
    }

    public double getWingLength() {
        return wingLength;
    }

    public void setWingLength(double wingLength) {
        this.wingLength = wingLength;
    }
}
