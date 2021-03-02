package by.epam.homeappliance.home.sorter;

import java.util.Comparator;

import by.epam.homeappliance.appliances.Appliance;

public class ByCapacityComparator implements Comparator<Appliance>{
    @Override
    public int compare(Appliance app1, Appliance app2) {
        return Double.compare(app1.getCapacityValue(), app2.getCapacityValue());
    }
}
