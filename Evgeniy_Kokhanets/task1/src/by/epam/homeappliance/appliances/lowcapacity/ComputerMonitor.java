package by.epam.homeappliance.appliances.lowcapacity;

public class ComputerMonitor extends LowCapacity{
    private String resolution;

    public ComputerMonitor(String name, double voltage, double amperage, String size, String resolution) {
        super(name, voltage, amperage, size);
        this.resolution = resolution;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "ComputerMonitor [Name=" + "\"" + getName() + "\"" + 
                ", Resolution=" + resolution + 
                ", Size=" + getSize() + 
                ", Voltage=" + getVoltage() + 
                ", Amperage=" + getAmperage() + 
                ", Capacity= " + getCapacityValue() + 
                "]";
    }
}
