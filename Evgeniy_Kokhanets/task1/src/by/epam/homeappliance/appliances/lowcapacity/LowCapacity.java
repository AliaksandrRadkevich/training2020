package by.epam.homeappliance.appliances.lowcapacity;

import by.epam.homeappliance.appliances.Appliance;

public abstract class LowCapacity extends Appliance{
    private String size;

    public LowCapacity(String name, double voltage, double amperage, String size) {
        super(name, voltage, amperage);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
