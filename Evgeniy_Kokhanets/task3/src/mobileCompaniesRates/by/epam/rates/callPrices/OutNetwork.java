package mobileCompaniesRates.by.epam.rates.callPrices;

public class OutNetwork extends CallPrices{
    private Double outNetwork;

    @Override
    public void setValue(String value) {
        this.outNetwork = Double.parseDouble(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.outNetwork);
    }

    @Override
    public String toString() {
        return "OutNetwork [outNetwork=" + outNetwork + "]";
    }

    
    
}
