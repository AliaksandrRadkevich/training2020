package by.training.domain.abstractions;

import java.util.List;

public abstract class ComplexUnit implements LanguageUnit {
    protected String unit;
    protected List<LanguageUnit> componentsList;

    public ComplexUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String getUnitString() {
        return unit;
    }

    public void setComponentsList(List<LanguageUnit> componentsList) {
        this.componentsList = componentsList;
    }

    public abstract List<LanguageUnit> getComponentsList();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexUnit)) return false;
        ComplexUnit that = (ComplexUnit) o;
        if (unit != null ? !unit.equalsIgnoreCase(that.unit) : that.unit != null) return false;
        return componentsList != null ? componentsList.equals(that.componentsList) : that.componentsList == null;
    }

    @Override
    public int hashCode() {
        int result = unit != null ? unit.hashCode() : 0;
        result = 31 * result + (componentsList != null ? componentsList.hashCode() : 0);
        return result;
    }
}