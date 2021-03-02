package by.epam.homeappliance.appliances.lowcapacity;

public class Hairdryer extends LowCapacity{
    private double airHeatLevel;

    public Hairdryer(String name, double voltage, double amperage, String size, double airHeatLevel) {
        super(name , voltage, amperage, size);
        this.airHeatLevel = airHeatLevel;
    }

    public double getAirHeatLevel() {
        return airHeatLevel;
    }

    public void setAirHeatLevel(double airHeatLevel) {
        this.airHeatLevel = airHeatLevel;
    }

    @Override
    public String toString() {
        return "Hairdryer [Name=" + "\"" + getName() + "\"" +
                ", AirHeatLevel=" + airHeatLevel +
                ", Size=" + getSize() + 
                ", Voltage=" + getVoltage() + 
                ", Amperage="+ getAmperage() + 
                ", Capacity=" + getCapacityValue()+ 
                "]";
    }
}
