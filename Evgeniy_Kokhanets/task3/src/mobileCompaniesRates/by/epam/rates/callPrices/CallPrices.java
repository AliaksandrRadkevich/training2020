package mobileCompaniesRates.by.epam.rates.callPrices;

public abstract class CallPrices {
    private String name;
    private String element;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getElement() {
        return element;
    }
    public void setElement(String element) {
        this.element = element;
    }
    
    abstract public void setValue(String value);
    
    abstract public String getValue();
}
