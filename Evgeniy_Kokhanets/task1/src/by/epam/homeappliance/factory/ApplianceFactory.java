package by.epam.homeappliance.factory;

import by.epam.homeappliance.appliances.Appliance;
import by.epam.homeappliance.appliances.highcapacity.Crockpot;
import by.epam.homeappliance.appliances.highcapacity.Microwave;
import by.epam.homeappliance.appliances.lowcapacity.ComputerMonitor;
import by.epam.homeappliance.appliances.lowcapacity.Hairdryer;

public abstract class ApplianceFactory {
    public static Appliance createAppliance(ApplianceType type) {
        Appliance appliance = null;
        switch (type) {
            case HAIRDRYER:
                appliance = new Hairdryer("Philips", 220, 0.5, "Small", 123);
                break;
            case COMPUTER_MONITOR:
                appliance = new ComputerMonitor("Samsung", 14, 1.5, "Large", "1920x1080");
                break;
            case MICROWAVE: 
                appliance = new Microwave("LG", 220, 4, 10, 150);
                break;
            case CROCKPOT: 
                appliance = new Crockpot("Gefest", 220, 5.6, 50, 4);
                break;
        }
        return appliance;
    }
}
