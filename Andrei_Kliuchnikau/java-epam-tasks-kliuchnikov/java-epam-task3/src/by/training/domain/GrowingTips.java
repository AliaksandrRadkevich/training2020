package by.training.domain;

public class GrowingTips {
    private int temperature;
    private boolean lighting;
    private int irrigation;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature < -40 || temperature > 40) {
            throw new UnsupportedOperationException("Average temperature can't be so big or small!");
        } else {
            this.temperature = temperature;
        }
    }

    public boolean isLighting() {
        return lighting;
    }

    public void setLighting(boolean lighting) {
        this.lighting = lighting;
    }

    public int getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(int irrigation) {
        if (irrigation < 1) {
            throw new UnsupportedOperationException("Irrigation can't be less than 1");
        } else {
            this.irrigation = irrigation;
        }
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature (degree celsius)=" + temperature +
                ", lighting=" + lighting +
                ", irrigation (ml)=" + irrigation +
                '}';
    }
}