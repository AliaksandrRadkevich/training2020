package by.training.task3.domain;

public class RocketAirToGrnd extends Rocket{
    private int flightAltitude;

    public RocketAirToGrnd(byte amount, int flightAltitude) {
        super(amount);
        this.flightAltitude = flightAltitude;
    }

    public int getFlightAltitude() {
        return flightAltitude;
    }

    public void setFlightAltitude(int flightAltitude) {
        this.flightAltitude = flightAltitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + flightAltitude;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        RocketAirToGrnd other = (RocketAirToGrnd) obj;
        if (flightAltitude != other.flightAltitude)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[amount: "+ super.getAmount() + 
                ", flight altitude: " + flightAltitude +"m]";
    }
}
