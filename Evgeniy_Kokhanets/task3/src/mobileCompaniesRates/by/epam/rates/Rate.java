package mobileCompaniesRates.by.epam.rates;

import java.util.LinkedHashSet;
import java.util.Set;

import mobileCompaniesRates.by.epam.rates.callPrices.CallPrices;
import mobileCompaniesRates.by.epam.rates.params.Params;

public class Rate {
    private String id;
    private String name;
    private String operatorName;
    private Double payroll;
    private Set <CallPrices> callPrices = new LinkedHashSet<CallPrices>();
    private Double smsPrice;
    private Set <Params> params = new LinkedHashSet<Params>();
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public Double getPayroll() {
        return payroll;
    }
    public void setPayroll(Double payroll) {
        this.payroll = payroll;
    }
    
    public Double getSmsPrice() {
        return smsPrice;
    }
    public void setSmsPrice(Double smsPrice) {
        this.smsPrice = smsPrice;
    }
    
    public Set<CallPrices> getCallPrices() {
        return callPrices;
    }
    public Set<Params> getParams() {
        return params;
    }
    public void setCallPrices(Set <CallPrices> callPrices) {
        this.callPrices = callPrices;
    }

    public void setParams(Set <Params> params) {
        this.params = params;
    }
    @Override
    public String toString() {
        return "Rate [id=" + id + ", name=" + name + ", operatorName=" + operatorName + ", payroll=" + payroll
                + ", callPrices=" + callPrices + ", smsPrice=" + smsPrice + ", params=" + params + "]";
    }

    
    
}
