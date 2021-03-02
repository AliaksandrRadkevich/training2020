package by.training.enums;

public enum Freshness {
    BEST(1, "отличная"),
    NORMAL(2, "нормальная"),
    BAD(3, "плохая"),
    WORST(4, "отвратительная");

    private final int freshLevel;
    private final String cyrillicFreshness;

    Freshness(int freshLevel, String cyrillicFreshness) {
        this.freshLevel = freshLevel;
        this.cyrillicFreshness = cyrillicFreshness;
    }

    public int getFreshLevel() {
        return freshLevel;
    }

    public String getCyrillicFreshness() {
        return cyrillicFreshness;
    }
}
