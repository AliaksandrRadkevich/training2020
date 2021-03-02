package by.epam.homeappliance.appliances.highcapacity;

import by.epam.homeappliance.appliances.Appliance;

public abstract class HighCapacity extends Appliance{
    private double weight;
    
    public HighCapacity(String name, double voltage, double amperage, double weight) {
        super(name, voltage, amperage);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
