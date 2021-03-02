package mobileCompaniesRates.by.epam.rates.params;

public class ConnectionFees extends Params{
    private Double connectionFees;

    @Override
    public void setValue(String value) {
        this.connectionFees = Double.valueOf(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.connectionFees);
    }

    @Override
    public String toString() {
        return "ConnectionFees [connectionFees=" + connectionFees + "]";
    }
    
    

}
