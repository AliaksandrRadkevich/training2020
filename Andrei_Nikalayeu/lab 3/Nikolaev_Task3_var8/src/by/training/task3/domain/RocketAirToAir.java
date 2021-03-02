package by.training.task3.domain;

public class RocketAirToAir extends Rocket{
    private int rangeOfFlight;

    public RocketAirToAir(byte amount, int rangeOfFlight) {
        super(amount);
        this.rangeOfFlight = rangeOfFlight;
    }

    public int getRangeOfFlight() {
        return rangeOfFlight;
    }

    public void setAmount(int rangeOfFlight) {
        this.rangeOfFlight = rangeOfFlight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + rangeOfFlight;
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
        RocketAirToAir other = (RocketAirToAir) obj;
        if (rangeOfFlight != other.rangeOfFlight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[amount: "+ super.getAmount() + 
                ", range of flight: " + rangeOfFlight +"m]";
    }
}
