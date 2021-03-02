package by.epam.homeappliance.appliances.highcapacity;

public class Microwave extends HighCapacity{
    private double heatLevel;

    public Microwave(String name, double voltage, double amperage, double weight, double heatLevel) {
        super(name , voltage, amperage, weight);
        this.heatLevel = heatLevel;
    }

    public double getHeatLevel() {
        return heatLevel;
    }

    public void setHeatLevel(double heatLevel) {
        this.heatLevel = heatLevel;
    }
    
    @Override
    public String toString() {
        return "Microwave [Name=" + "\"" + getName() + "\"" +
                ", HeatLevel=" + heatLevel + 
                ", Weight=" + getWeight() + 
                ", Voltage=" + getVoltage() + 
                ", Amperage="+ getAmperage() + 
                ", Capacity=" + getCapacityValue()+ 
                "]";
    }
}
