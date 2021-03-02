package by.training.task3.domain;

public class Gun {
    private int caliber;

    public Gun(int caliber) {
        this.caliber = caliber;
    }

    public int getCaliber() {
        return caliber;
    }

    public void setCaliber(int caliber) {
        this.caliber = caliber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + caliber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Gun other = (Gun) obj;
        if (caliber != other.caliber)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[caliber: " + caliber + "mm]";
    }
}
