package by.training.airline.logic;

import by.training.airline.domain.AirTransport;

public class FuelConsumptionFilter implements Filter {
    private Double minValue;
    private Double maxValue;

    public FuelConsumptionFilter(Double minValue, Double maxValue) {
        if (minValue >= 0 && maxValue >= 0 && minValue <= maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }
    }

    public Double getMinValue() {
        return minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public boolean isAppropriate(AirTransport airTransport) {
        return minValue <= airTransport.getFuelConsumption() && airTransport.getFuelConsumption() <= maxValue;
    }
}
