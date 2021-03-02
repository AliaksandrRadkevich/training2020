package by.epam.homeappliance.appliances;

public abstract class Appliance{
    private String name;
    private double voltage;
    private double amperage;
    
    public Appliance(String name, double voltage, double amperage) {
        this.name = name;
        this.voltage = voltage;
        this.amperage = amperage;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVoltage() {
        return voltage;
    }
    
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }
    
    public double getAmperage() {
        return amperage;
    }
    
    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }
    
    public double getCapacityValue() {
        return this.amperage * this.voltage;
    }
}
