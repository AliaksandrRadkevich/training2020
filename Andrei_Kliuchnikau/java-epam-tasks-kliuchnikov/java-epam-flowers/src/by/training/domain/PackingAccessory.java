package by.training.domain;

import java.io.Serializable;

public class PackingAccessory implements Serializable {
    private String name;
    private int minCapacity;
    private int maxCapacity;
    private boolean isUse;

    public PackingAccessory(String name, int minCapacity, int maxCapacity) {
        this.name = name;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        isUse = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }
}
