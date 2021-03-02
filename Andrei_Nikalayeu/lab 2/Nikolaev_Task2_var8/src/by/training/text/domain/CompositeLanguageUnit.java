package by.training.text.domain;

import java.util.ArrayList;
import java.util.List;

public class CompositeLanguageUnit implements LanguageUnit{
    private List<LanguageUnit> components = new ArrayList<>();

    public void addComponent(LanguageUnit component) {
        components.add(component);
    }

    public void removeComponent(LanguageUnit component) {
        components.remove(component);
    }

    public List<LanguageUnit> getComponents() {
        return components;
    }

    @Override
    public String get() {
        StringBuilder stringBuilder = new StringBuilder();

        for (LanguageUnit component : components) {
            stringBuilder.append(component.get());
        }

        return stringBuilder.toString();
    }
}
