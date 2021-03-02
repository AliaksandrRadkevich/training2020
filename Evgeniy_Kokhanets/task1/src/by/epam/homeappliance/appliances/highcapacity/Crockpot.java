package by.epam.homeappliance.appliances.highcapacity;

public class Crockpot extends HighCapacity{
    private int numOfBurners;
    
    public Crockpot(String name, double voltage, double amperage, double weight, int numOfBurners) {
        super(name , voltage, amperage, weight);
        this.numOfBurners = numOfBurners;
    }

    public int getNumOfBurners() {
        return numOfBurners;
    }

    public void setNumOfBurners(int numOfBurners) {
        this.numOfBurners = numOfBurners;
    }
    
    @Override
    public String toString() {
        return "Crockpot [Name=" + "\"" + getName() + "\"" +
                ", NumOfBurners=" + numOfBurners +
                ", Weight=" + getWeight() + 
                ", Voltage=" + getVoltage() + 
                ", Amperage="+ getAmperage() + 
                ", Capacity=" + getCapacityValue()+ 
                "]";
    }
}
