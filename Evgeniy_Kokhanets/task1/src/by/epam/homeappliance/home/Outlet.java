package by.epam.homeappliance.home;

import java.util.List;
import by.epam.homeappliance.appliances.Appliance;
import by.epam.homeappliance.home.sorter.ApplianceSort;
import by.epam.homeappliance.home.sorter.ByCapacityComparator;
import java.util.ArrayList;
import java.util.Collections;

public class Outlet implements ApplianceSort {
    private List<Appliance> appliances;
    
    public Outlet() {
        this.appliances = new ArrayList<>();
    }
    
    public List<Appliance> getAppliances(){
        return this.appliances;
    }
    
    public void plugIn(Appliance appliance) {
        appliances.add(appliance);
    }
    
    public void plugOut(Appliance appliance) {
        appliances.remove(appliance);
    }
    
    public void getApplianceByIndex(int index) {
        System.out.println(appliances.get(index));
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
