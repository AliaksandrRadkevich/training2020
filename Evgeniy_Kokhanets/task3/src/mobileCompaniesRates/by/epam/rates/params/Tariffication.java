package mobileCompaniesRates.by.epam.rates.params;

public class Tariffication extends Params{
    private TarifficationType tarifficationType;

    @Override
    public void setValue(String value) {
        this.tarifficationType = TarifficationType.valueOf(value);
    }

    @Override
    public String getValue() {
        return String.valueOf(this.tarifficationType);
    }

    @Override
    public String toString() {
        return "Tariffication [tarifficationType=" + tarifficationType + "]";
    }
    
    

}
