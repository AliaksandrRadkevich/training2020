package by.training.task3.printer;

import by.training.task3.domain.Airplane;

public class ConsolePrinter implements Printer{
    @Override
    public void print(Airplane airplane) {
        String getGun = airplane.getGun() == null ? "N/A" : airplane.getGun().toString();
        String getRocketAirToAir = airplane.getRocketAirToAir() == null ? "N/A": airplane.getRocketAirToAir().toString();
        String getRocketAirToGrnd = airplane.getRocketAirToGrnd() == null ? "N/A": airplane.getRocketAirToGrnd().toString();
        String getRadar = airplane.getRadar() == null ? "N/A": airplane.getRadar().toString();

        System.out.println("Airplane" +
                            "\n[ ID: " + airplane.getId() + 
                            ",\n  Model: " + airplane.getModel() + 
                            ",\n  Origin: " + airplane.getOrigin() + 
                            ",\n  Type: " + airplane.getAirplaneType() + 
                            ",\n  Seats amount: " + airplane.getSeats() + 
                            ",\n  Gun: " + getGun + 
                            ",\n  Rocket air to air: " + getRocketAirToAir + 
                            ",\n  Rocket air to ground: " + getRocketAirToGrnd + 
                            ",\n  Radar: " + getRadar + 
                            ",\n  Length: " + airplane.getLength() + 
                            "m,\n  Width: " + airplane.getWidth() + 
                            "m,\n  Height: " + airplane.getHeight() + 
                            "m,\n  Price: " + airplane.getPrice() + "tl. ]\n");
    }

    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
