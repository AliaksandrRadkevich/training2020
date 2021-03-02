package mobileCompaniesRates.by.epam.rates.callPrices;

public class InNetwork extends CallPrices{
    private Double inNetwork;

    @Override
    public void setValue(String value) {
        this.inNetwork = Double.parseDouble(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.inNetwork);
    }

    @Override
    public String toString() {
        return "InNetwork [inNetwork=" + inNetwork + "]";
    }
    
    
}
