package by.training.text.domain;

public class SimpleLanguageUnit implements LanguageUnit{
    private String component;

    public SimpleLanguageUnit(String component) {
        this.component = component;
    }

    public void setSymbol(String component) {
        this.component = component;
    }

    @Override
    public String get() {
        return "{" + component + "}";
    }
}
