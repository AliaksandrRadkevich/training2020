package by.training.domain.abstractions;

public abstract class SimpleUnit implements LanguageUnit {
    protected Character unit;

    public char getUnit() {
        return unit;
    }

    public void setUnit(char unit) {
        this.unit = unit;
    }

    @Override
    public String getUnitString() {
        return Character.toString(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleUnit)) return false;
        SimpleUnit that = (SimpleUnit) o;
        return unit != null ? unit.equals(that.unit) : that.unit == null;
    }

    @Override
    public int hashCode() {
        return unit != null ? unit.hashCode() : 0;
    }
}