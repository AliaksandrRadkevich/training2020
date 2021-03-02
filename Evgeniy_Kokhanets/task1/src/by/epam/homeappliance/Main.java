package by.epam.homeappliance;

import by.epam.homeappliance.home.Outlet;
import by.epam.homeappliance.home.AppliancesOnHand;
import by.epam.homeappliance.factory.ApplianceType;

public class Main {
    public static void main(String[] args) {
        AppliancesOnHand appliancesOnHand = new AppliancesOnHand();
        appliancesOnHand.addNewAppliance(ApplianceType.HAIRDRYER);
        appliancesOnHand.addNewAppliance(ApplianceType.MICROWAVE);
        appliancesOnHand.addNewAppliance(ApplianceType.COMPUTER_MONITOR);
        appliancesOnHand.addNewAppliance(ApplianceType.CROCKPOT);
        
        System.out.println("The appliances on hand sorted by capacity:");
        appliancesOnHand.sortByCapacity();
        appliancesOnHand.getAppliances().forEach(appliance -> {
            System.out.println(appliance);
        });
        
        Outlet outlet = new Outlet();
        outlet.plugIn(appliancesOnHand.getApplianceByName("Philips"));
        outlet.plugIn(appliancesOnHand.getApplianceByName("Samsung"));
        outlet.plugIn(appliancesOnHand.getApplianceByName("Gefest"));
        outlet.plugIn(appliancesOnHand.getApplianceByName("LG"));
        
        System.out.println("\nThe appliances plugged in to the outlet:");
        outlet.getAppliances().forEach(appliance -> {
            System.out.println(appliance);
        });
        
        System.out.println("\nThe capacity of the following appliance:");
        System.out.println(outlet.getApplianceByName("LG").getCapacityValue());
        
        System.out.println("\nThe appliance got by set parameters:");
        System.out.println(outlet.getApplianceByParams("LG", 220, 4));
    }
}
