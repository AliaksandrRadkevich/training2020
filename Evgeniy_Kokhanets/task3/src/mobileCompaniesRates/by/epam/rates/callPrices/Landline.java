package mobileCompaniesRates.by.epam.rates.callPrices;

public class Landline extends CallPrices{
    private Double landline;

    @Override
    public void setValue(String value) {
        this.landline = Double.parseDouble(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.landline);
    }

    @Override
    public String toString() {
        return "Landline [landline=" + landline + "]";
    }

    
    
}
