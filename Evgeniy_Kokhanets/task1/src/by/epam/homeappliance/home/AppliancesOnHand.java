package by.epam.homeappliance.home;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import by.epam.homeappliance.appliances.Appliance;
import by.epam.homeappliance.factory.ApplianceFactory;
import by.epam.homeappliance.factory.ApplianceType;
import by.epam.homeappliance.home.sorter.ByCapacityComparator;
import by.epam.homeappliance.home.sorter.ApplianceSort;

public class AppliancesOnHand implements ApplianceSort {
    private List<Appliance> appliances;
    
    public AppliancesOnHand() {
        this.appliances = new ArrayList<>();
    }
    
    public List<Appliance> getAppliances(){
        return this.appliances;
    }
    
    public void addNewAppliance(ApplianceType type) {
        appliances.add(ApplianceFactory.createAppliance(type));
    }
    
    public void removeAppliance(Appliance appliance) {
        appliances.remove(appliance);
    }
    
    public Appliance getApplianceByIndex(int index) {
        return appliances.get(index);
    }
    
    public Appliance getApplianceByName(String name) {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).getName().equals(name)) {
                return appliances.get(i);
            }
        }
        return null;
    }
    
    public Appliance getApplianceByParams(String name, double voltage, double amperage) {
        for (int i = 0; i < appliances.size(); i++) {
            if (appliances.get(i).getName().equals(name) &&
                appliances.get(i).getVoltage() == voltage &&
                appliances.get(i).getAmperage() == amperage) {
                return appliances.get(i);
            }
        }
        return null;
    }
    
    @Override
    public void sortByCapacity() {
        Collections.sort(appliances, new ByCapacityComparator());
    }
}
